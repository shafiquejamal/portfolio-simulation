package com.eigenroute.portfoliosimulation

import akka.actor.ActorRef

class PortfolioSimulator(reaper: ActorRef) extends WatchedActor(reaper) {

  override def receive = {
    case simulationParametersWithInvestmentPeriod: SimulationParametersWithInvestmentPeriod =>
      // log.info(s"\n\nPortfolioSimulator (SimulationParametersWithInvestmentPeriod): ${simulationParametersWithInvestmentPeriod.investmentPeriod}")
      val simulationParameters = simulationParametersWithInvestmentPeriod.simulationParameters
      val rebalancePortfolio =
        new Investment(
          simulationParametersWithInvestmentPeriod.investmentPeriod,
          simulationParameters.rebalancingInterval,
          simulationParameters.initialInvestment,
          simulationParameters.perTransactionTradingCost,
          simulationParameters.bidAskCostFractionOfNav,
          simulationParameters.portfolioDesign,
          simulationParameters.maxAllowedDeviation,
          simulationParameters.sortedCommonDatesETFData
        ).rebalancePortfolio
      sender() ! rebalancePortfolio
  }

}
