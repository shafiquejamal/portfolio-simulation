package com.eigenroute.portfoliosimulation

import com.eigenroute.portfoliosimulation.portfoliocalculation.{FinalPortfolioQuantityToHave, PortfolioPerformance}

case class RebalancedPortfolio(
    rebalancedETFData: Seq[ETFData],
    newQuantitiesChosenForThisRebalancing: Seq[FinalPortfolioQuantityToHave],
    accumulatedExDiv: BigDecimal,
    accumulatedCash: BigDecimal,
    endOfPeriodSnapshot: PortfolioSnapshot,
    initialInvestment: BigDecimal,
    portfolioPerformance: PortfolioPerformance,
    liquidatedValue: BigDecimal = 0,
    totalReturnFraction: BigDecimal = 0)
