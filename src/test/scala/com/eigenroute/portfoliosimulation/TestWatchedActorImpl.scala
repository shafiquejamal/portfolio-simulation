package com.eigenroute.portfoliosimulation

import akka.actor.ActorRef

class TestWatchedActorImpl(reaperRef: ActorRef) extends WatchedActor(reaperRef) {
  override def receive = {
    case any: Any =>
  }
}
