package com.eigenroute.portfoliosimulation.investmentperiod

import akka.actor.ActorRef
import com.eigenroute.portfoliosimulation.investmentperiod.InvestmentPeriodsGenerator.GenerateInvestmentPeriods
import com.eigenroute.portfoliosimulation.util.WatchedActor
import com.eigenroute.portfoliosimulation.{ETFData, PortfolioDesign}

object InvestmentPeriodsGenerator {

  case class GenerateInvestmentPeriods(
      portfolioDesign: PortfolioDesign,
      sortedCommonDatesETFData:Seq[ETFData],
      investmentDurationYears: Int)

}

class InvestmentPeriodsGenerator(reaper: ActorRef) extends WatchedActor(reaper) {

  override def receive = {
    case generateInvestmentPeriods: GenerateInvestmentPeriods =>
      log.info(s"\n\nInvestmentPeriodsGenerator (GenerateInvestmentPeriods): ${generateInvestmentPeriods.portfolioDesign}")
      val investmentPeriods = new InvestmentPeriodsCreator(
        generateInvestmentPeriods.portfolioDesign,
        generateInvestmentPeriods.sortedCommonDatesETFData,
        generateInvestmentPeriods.investmentDurationYears).create
      sender() ! InvestmentPeriods(investmentPeriods)
      context.stop(self)
  }

}
