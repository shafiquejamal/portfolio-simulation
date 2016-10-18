package com.eigenroute.portfoliosimulation

import akka.actor.ActorRef
import org.joda.time.DateTime

class InvestmentPeriodsGenerator(reaper: ActorRef) extends WatchedActor(reaper) {

  override def receive = {
    case anyMessage:String =>
      log.info(s"\n\n InvestmentPeriodsGenerator:  received message: $anyMessage")
      val startDate = new DateTime(2010, 1, 1, 0, 0, 0)
      val investmentPeriods = 0.to(2).toSeq.map { n =>
        InvestmentPeriod(startDate.plusDays(n), startDate.plusDays(n).plusYears(5))
      }
      sender() ! InvestmentPeriods(investmentPeriods)
      context.stop(self)
  }

}
