package com.eigenroute.portfoliosimulation

import akka.actor.{ActorRef, Actor, ActorLogging}

class PortfolioSimulationManager(
  investmentPeriodsGenerator: ActorRef,
  routerToSimulator: ActorRef) extends Actor with ActorLogging {

  override def receive = {
    case parameters: String =>
      log.info(s"parameters: $parameters")
      investmentPeriodsGenerator ! "give me investment periods"
    case investmentPeriods: InvestmentPeriods =>
      log.info(s"Received $InvestmentPeriods")
      investmentPeriods.investmentPeriods foreach ( investmentPeriod => routerToSimulator ! investmentPeriod )

  }

}
