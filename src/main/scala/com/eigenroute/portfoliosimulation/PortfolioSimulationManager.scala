package com.eigenroute.portfoliosimulation

import akka.actor.{ActorRef, PoisonPill}
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
      log.info(s"\n\nPortfolioSimulationManager (SimulationParameters): ${parameters.portfolioDesign}")
      simulationParameters = parameters
      val generateInvestmentPeriods =
        GenerateInvestmentPeriods(
         parameters.portfolioDesign,
         parameters.sortedCommonDatesETFData,
         parameters.investmentDurationYears)
      investmentPeriodsGenerator ! generateInvestmentPeriods
    case investmentPeriods: InvestmentPeriods =>
      log.info(s"\n\nPortfolioSimulationManager (InvestmentPeriods): ${investmentPeriods.investmentPeriods.size}")
      investmentPeriodsToProcess = investmentPeriods.investmentPeriods
      val simulationParametersWithInvestmentPeriods =
        investmentPeriodsToProcess.map (investmentPeriod =>
          SimulationParametersWithInvestmentPeriod(investmentPeriod, simulationParameters)
        )
      simulationParametersWithInvestmentPeriods foreach ( parametersAndInvestmentPeriods =>
        routerToSimulator ! parametersAndInvestmentPeriods )
    case rebalancedPortfolio: RebalancedPortfolio =>
      // log.info(s"\n\nPortfolioSimulationManager (RebalancedPortfolio): ${rebalancedPortfolio.portfolioPerformance}")
      investmentPeriodsProcessed = investmentPeriodsProcessed + rebalancedPortfolio.portfolioPerformance.investmentPeriod
      resultsWriter ! rebalancedPortfolio
      // log.info(s"\n\nPortfolioSimulationManager (RebalancedPortfolio): ${investmentPeriodsProcessed.size} >= ${investmentPeriodsToProcess.size} ?")
      if (investmentPeriodsProcessed.size >= investmentPeriodsToProcess.size) {
        context.stop(routerToSimulator)
        resultsWriter ! PoisonPill
        context.stop(self)
      }

  }

}
