package com.eigenroute.portfoliosimulation

import org.joda.time.{Days, DateTime}

class InvestmentPeriodsCreator(
    portfolioDesign: PortfolioDesign,
    sortedCommonDatesETFData:Seq[ETFData],
    investmentDurationYears: Int) {

  val allDates: List[DateTime] =
    sortedCommonDatesETFData.toList.map(_.asOfDate)
    .distinct.sortWith( (d1, d2) => d1.isBefore(d2))
  val maybeEarliestDate: Option[DateTime] = allDates.headOption
  val maybeLatestDate: Option[DateTime] = allDates.reverse.headOption

  def create:Seq[InvestmentPeriod] =
    (for {
      earliestDate <- maybeEarliestDate.toSeq
      latestDate <- maybeLatestDate.toSeq
      daysBetween =
      Days.daysBetween(
        earliestDate.toLocalDate,
        latestDate.minusYears(investmentDurationYears).plusDays(1).toLocalDate)
      .getDays
      day <- 0.to(daysBetween)
      if !earliestDate.plusYears(investmentDurationYears).isAfter(latestDate)
      startOfPeriod = earliestDate.plusDays(day)
      endOfPeriod = earliestDate.plusYears(investmentDurationYears).plusDays(day)
    } yield {
      InvestmentPeriod(startOfPeriod, endOfPeriod)
    }).filter(iP =>
      allDates.contains(iP.startDate)
    )
}
