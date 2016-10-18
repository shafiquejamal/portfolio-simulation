package com.eigenroute.portfoliosimulation

import akka.actor.ActorSystem

object Main extends App {
  val system = ActorSystem("PortfolioSimulationActorSystem")

  system.awaitTermination()
}