package com.eigenroute.portfoliosimulation

case class PortfolioPerformance(
    investmentPeriod: InvestmentPeriod,
    averageAnnualReturnFraction: BigDecimal = 0)
