package com.eigenroute.portfoliosimulation

import akka.actor.{ActorLogging, Actor, ActorRef}
import com.eigenroute.portfoliosimulation.Reaper.WatchMe

abstract class WatchedActor(reaper: ActorRef) extends Actor with ActorLogging {

  reaper ! WatchMe(self)

}
