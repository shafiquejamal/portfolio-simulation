package com.eigenroute.portfoliosimulation

case class RebalancedPortfolio(
    investmentPeriod: InvestmentPeriod,
    rebalancedETFData: Seq[ETFData],
    newQuantitiesChosenForThisRebalancing: Seq[FinalPortfolioQuantityToHave],
    accumulatedExDiv: BigDecimal,
    accumulatedCash: BigDecimal,
    endOfPeriodSnapshot: PortfolioSnapshot,
    initialInvestment: BigDecimal,
    portfolioPerformance: PortfolioPerformance,
    liquidatedValue: BigDecimal = 0,
    totalReturnFraction: BigDecimal = 0)
