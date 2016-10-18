package com.eigenroute.portfoliosimulation

import org.joda.time.{DateTime, Months, Years}

case class InvestmentPeriod(startDate: DateTime, endDate: DateTime) {
  require(startDate.isBefore(endDate))
  require(InvestmentPeriod.lengthInYears(this) == InvestmentPeriod.lengthInMonths(this) / 12)
}

object InvestmentPeriod {
  def lengthInMonths(investmentPeriod: InvestmentPeriod): Int =
    Months.monthsBetween(investmentPeriod.startDate.toLocalDate, investmentPeriod.endDate.toLocalDate).getMonths

  def lengthInYears(investmentPeriod: InvestmentPeriod): Int =
    Years.yearsBetween(investmentPeriod.startDate.toLocalDate, investmentPeriod.endDate.toLocalDate).getYears
}