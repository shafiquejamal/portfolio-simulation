package com.eigenroute.portfoliosimulation

import akka.actor.{Actor, ActorRef}
import com.eigenroute.portfoliosimulation.Reaper.WatchMe

abstract class WatchedActor(reaper: ActorRef) extends Actor {

  reaper ! WatchMe(self)

}
