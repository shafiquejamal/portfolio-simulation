package com.eigenroute.portfoliosimulation

import akka.actor.{PoisonPill, ActorRef}

class PortfolioSimulationManager(
  reaper: ActorRef,
  investmentPeriodsGenerator: ActorRef,
  routerToSimulator: ActorRef,
  resultsWriter: ActorRef) extends WatchedActor(reaper) {

  var investmentPeriodsToProcess: Seq[InvestmentPeriod] = Seq.empty
  var investmentPeriodsProcessed: Set[InvestmentPeriod] = Set.empty

  override def receive = {
    case parameters: String =>
      log.info(s"\n\n PortfolioSimulationManager: parameters: $parameters")
      investmentPeriodsGenerator ! "give me investment periods"
    case investmentPeriods: InvestmentPeriods =>
      log.info(s"\n\n PortfolioSimulationManager: Received $InvestmentPeriods")
      investmentPeriodsToProcess = investmentPeriods.investmentPeriods
      investmentPeriods.investmentPeriods foreach ( investmentPeriod => routerToSimulator ! investmentPeriod )
    case rebalancedPortfolio: RebalancedPortfolio =>
      investmentPeriodsProcessed = investmentPeriodsProcessed + rebalancedPortfolio.investmentPeriod
      resultsWriter ! rebalancedPortfolio
      if (investmentPeriodsProcessed.size >= investmentPeriodsToProcess.size) {
        context.stop(routerToSimulator)
        resultsWriter ! PoisonPill
        context.stop(self)
      }

  }

}
