package com.eigenroute.portfoliosimulation.util

import akka.actor.ActorRef

class TestWatchedActorImpl(reaperRef: ActorRef) extends WatchedActor(reaperRef) {
  override def receive = {
    case any: Any =>
  }
}
