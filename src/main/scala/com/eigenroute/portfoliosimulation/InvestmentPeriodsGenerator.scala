package com.eigenroute.portfoliosimulation

import akka.actor.ActorRef
import com.eigenroute.portfoliosimulation.InvestmentPeriodsGenerator.GenerateInvestmentPeriods

object InvestmentPeriodsGenerator {

  case class GenerateInvestmentPeriods(
      portfolioDesign: PortfolioDesign,
      sortedCommonDatesETFData:Seq[ETFData],
      investmentDurationYears: Int)

}

class InvestmentPeriodsGenerator(reaper: ActorRef) extends WatchedActor(reaper) {

  override def receive = {
    case generateInvestmentPeriods:GenerateInvestmentPeriods =>
      log.info(s"\n\n InvestmentPeriodsGenerator:  received message: $generateInvestmentPeriods")
      val foo = new InvestmentPeriodsCreator(
        generateInvestmentPeriods.portfolioDesign,
        generateInvestmentPeriods.sortedCommonDatesETFData,
        generateInvestmentPeriods.investmentDurationYears).create
      sender() ! InvestmentPeriods(foo)
      context.stop(self)
  }

}
