package com.eigenroute.portfoliosimulation

import akka.actor.{Actor, ActorLogging, ActorRef, Terminated}

object Reaper {

  case class WatchMe(ref: ActorRef)

}

abstract class Reaper extends Actor with ActorLogging {

  import Reaper._

  var watched = Vector[ActorRef]()

  def allSoulsReaped(): Unit

  // Watch and check for termination
  final def receive = {
    case WatchMe(ref) =>
      context.watch(ref)
      watched = watched :+ ref
    case Terminated(ref) =>
      watched = watched filterNot ref.==
      if (watched.isEmpty) allSoulsReaped()
  }

}
