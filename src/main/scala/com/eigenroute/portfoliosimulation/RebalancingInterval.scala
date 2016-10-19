package com.eigenroute.portfoliosimulation

import scala.language.implicitConversions

sealed trait RebalancingInterval {
  def months:Int
}

object RebalancingInterval extends {

  case object Monthly extends RebalancingInterval { override val months:Int = 1 }
  case object Quarterly extends RebalancingInterval { override val months:Int = 3 }
  case object SemiAnnually extends RebalancingInterval { override val months:Int = 6 }
  case object Annually extends RebalancingInterval { override val months:Int = 12 }

  implicit def rebalancingIntervalToNumberOfMonths(rebalancingPeriod: RebalancingInterval):Int = rebalancingPeriod.months

  val rebalancingIntervals = Vector(Monthly, Quarterly, SemiAnnually, Annually)

  def rebalancingInterval(interval: String): RebalancingInterval =
    rebalancingIntervals.find(rebalancingInterval => rebalancingInterval.toString.toLowerCase == interval.toLowerCase())
    .getOrElse(SemiAnnually)

}
