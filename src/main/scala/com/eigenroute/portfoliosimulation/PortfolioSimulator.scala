package com.eigenroute.portfoliosimulation

import akka.actor.{ActorLogging, Actor}

class PortfolioSimulator extends Actor with ActorLogging {

  override def receive = {
    case message: Any =>
      log.info(s"Portfolio simulator received message: $message")
  }

}
