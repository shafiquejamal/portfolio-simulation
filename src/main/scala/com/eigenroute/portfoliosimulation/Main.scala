package com.eigenroute.portfoliosimulation

import akka.actor.{ActorSystem, Props}
import akka.routing.SmallestMailboxPool

object Main extends App {
  val system = ActorSystem("PortfolioSimulationActorSystem")

  val investmentPeriodsGenerator = system.actorOf(Props[InvestmentPeriodsGenerator], "investmentPeriodsGenerator")
  val routerToSimulator = system.actorOf(Props[PortfolioSimulator].withRouter(SmallestMailboxPool(nrOfInstances = 8)))
  val portfolioSimulationManager =
    system.actorOf(Props(classOf[PortfolioSimulationManager], investmentPeriodsGenerator, routerToSimulator), "portfolioSimulator")

  portfolioSimulationManager ! "here are your parameters"

  Thread.sleep(200)
  system.shutdown()
}