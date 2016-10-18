package com.eigenroute.portfoliosimulation

import akka.actor.{ActorSystem, Props}
import akka.routing.SmallestMailboxPool

object Main extends App {
  val system = ActorSystem("PortfolioSimulationActorSystem")

  val reaper = system.actorOf(Props[ReaperImpl], "reaper")

  val investmentPeriodsGenerator = system.actorOf(Props(classOf[InvestmentPeriodsGenerator], reaper), "investmentPeriodsGenerator")
  val routerToSimulator = system.actorOf(Props(classOf[PortfolioSimulator], reaper).withRouter(SmallestMailboxPool(nrOfInstances = 8)))
  val resultsWriter = system.actorOf(Props(classOf[ResultsWriter], reaper))

  val portfolioSimulationManager =
    system.actorOf(Props(classOf[PortfolioSimulationManager],
      reaper,
      investmentPeriodsGenerator,
      routerToSimulator,
      resultsWriter), "portfolioSimulator")

  portfolioSimulationManager ! "here are your parameters"

}