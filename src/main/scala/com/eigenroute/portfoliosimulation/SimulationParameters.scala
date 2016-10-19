package com.eigenroute.portfoliosimulation

case class SimulationParameters(
    portfolioDesign: PortfolioDesign,
    sortedCommonDatesETFData:Seq[ETFData],
    investmentDurationYears: Int,
    rebalancingInterval: RebalancingInterval,
    initialInvestment: BigDecimal,
    perTransactionTradingCost: BigDecimal,
    bidAskCostFractionOfNav: BigDecimal,
    maxAllowedDeviation: BigDecimal)
