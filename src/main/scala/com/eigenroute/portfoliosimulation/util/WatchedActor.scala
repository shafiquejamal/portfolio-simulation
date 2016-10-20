package com.eigenroute.portfoliosimulation.util

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.eigenroute.portfoliosimulation.util.Reaper.WatchMe

abstract class WatchedActor(reaper: ActorRef) extends Actor with ActorLogging {

  reaper ! WatchMe(self)

}
