package com.eigenroute.portfoliosimulation

import akka.actor.{Actor, ActorLogging}
import org.joda.time.DateTime

class InvestmentPeriodsGenerator extends Actor with ActorLogging {

  override def receive = {
    case anyMessage:Any =>
      log.info(s"received message: $anyMessage")
      val startDate = new DateTime(2010, 1, 1, 0, 0, 0)
      val investmentPeriods = 0.to(100).toSeq.map { n =>
        InvestmentPeriod(startDate.plusDays(n), startDate.plusDays(n).plusYears(5))
      }
      sender() ! InvestmentPeriods(investmentPeriods)
  }

}
