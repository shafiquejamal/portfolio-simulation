package com.eigenroute.portfoliosimulation.portfoliocalculation

import com.eigenroute.portfoliosimulation.investmentperiod.InvestmentPeriod

case class PortfolioPerformance(
    investmentPeriod: InvestmentPeriod,
    averageAnnualReturnFraction: BigDecimal = 0)
