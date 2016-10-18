package com.eigenroute.portfoliosimulation

class ReaperImpl extends Reaper {

  override def allSoulsReaped(): Unit = context.system.shutdown()

}
