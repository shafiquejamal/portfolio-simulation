package com.eigenroute.portfoliosimulation

import com.eigenroute.portfoliosimulation.RebalancingInterval._
import org.scalatest._

class RebalancingIntervalUTest extends FlatSpec with ShouldMatchers {

  "Converting a rebalancing interval to number of months" should "convert the rebalancing interval to the correct number " +
  "of months" in  {
    RebalancingInterval.rebalancingIntervalToNumberOfMonths(Monthly) shouldEqual 1
    RebalancingInterval.rebalancingIntervalToNumberOfMonths(Annually) shouldEqual 12
  }

  "Retrieving the rebalancing interval from a string" should "return the correct rebalancing interval" in {
    RebalancingInterval.rebalancingInterval("monthLy") shouldEqual Monthly
    RebalancingInterval.rebalancingInterval("aNnuallY") shouldEqual Annually
    RebalancingInterval.rebalancingInterval("non-existent") shouldEqual SemiAnnually
  }

}
