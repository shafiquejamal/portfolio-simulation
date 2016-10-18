package com.eigenroute.portfoliosimulation

import akka.actor.ActorRef

class PortfolioSimulator(reaper: ActorRef) extends WatchedActor(reaper) {

  override def receive = {
    case investmentPeriod: InvestmentPeriod =>
      log.info(s"\n\n Portfolio simulator received message: $investmentPeriod")
      sender() ! RebalancedPortfolio(investmentPeriod, Seq(), Seq(), 0, 0d, null, 0, null, 0, 0)
  }

}
