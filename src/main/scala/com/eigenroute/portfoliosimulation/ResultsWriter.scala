package com.eigenroute.portfoliosimulation

import akka.actor.ActorRef

class ResultsWriter(reaper: ActorRef) extends WatchedActor(reaper) {

  override def receive = {
    case rebalancedPortfolio: RebalancedPortfolio =>
      log.info(s"\n\n ResultsWriter: Got a result: ${rebalancedPortfolio.portfolioPerformance}")

  }

}
