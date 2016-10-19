package com.eigenroute.portfoliosimulation

import akka.actor.{PoisonPill, ActorRef}
import com.eigenroute.portfoliosimulation.InvestmentPeriodsGenerator.GenerateInvestmentPeriods

class PortfolioSimulationManager(
  reaper: ActorRef,
  investmentPeriodsGenerator: ActorRef,
  routerToSimulator: ActorRef,
  resultsWriter: ActorRef) extends WatchedActor(reaper) {

  var investmentPeriodsToProcess: Seq[InvestmentPeriod] = Seq.empty
  var investmentPeriodsProcessed: Set[InvestmentPeriod] = Set.empty
  var simulationParameters: SimulationParameters = _

  override def receive = {
    case parameters: SimulationParameters =>
      simulationParameters = parameters
      val generateInvestmentPeriods =
        GenerateInvestmentPeriods(
         parameters.portfolioDesign,
         parameters.sortedCommonDatesETFData,
         parameters.investmentDurationYears)
      investmentPeriodsGenerator ! generateInvestmentPeriods
    case investmentPeriods: InvestmentPeriods =>
      investmentPeriodsToProcess = investmentPeriods.investmentPeriods
      investmentPeriods.investmentPeriods foreach ( investmentPeriod => routerToSimulator ! investmentPeriod )
    case rebalancedPortfolio: RebalancedPortfolio =>
      log.info(s"\n\n PortfolioSimulationManager: Received $rebalancedPortfolio")
      investmentPeriodsProcessed = investmentPeriodsProcessed + rebalancedPortfolio.portfolioPerformance.investmentPeriod
      resultsWriter ! rebalancedPortfolio
      if (investmentPeriodsProcessed.size >= investmentPeriodsToProcess.size) {
        context.stop(routerToSimulator)
        resultsWriter ! PoisonPill
        context.stop(self)
      }
  }

}
