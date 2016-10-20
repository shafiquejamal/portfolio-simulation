package com.eigenroute.portfoliosimulation.util

class ReaperImpl extends Reaper {

  override def allSoulsReaped(): Unit = context.system.shutdown()

}
