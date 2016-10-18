package com.eigenroute.portfoliosimulation

import org.joda.time.DateTime

trait PortfolioFixture {

  val now = new DateTime(2016, 1, 1, 1, 1, 1)

  val eTFA = ETFCode("AAA")
  val eTFB = ETFCode("BBB")
  val eTFC = ETFCode("CCC")
  val eTFD = ETFCode("DDD")

  val eTFDataPlusA = ETFData(now, eTFA, "1", 20, 0, 50)
  val eTFDataPlusB = ETFData(now, eTFB, "1", 30, 0, 100)
  val eTFDataPlusC = ETFData(now, eTFC, "1", 40, 0, 100)
  val eTFDataPlusD = ETFData(now, eTFD, "1", 50, 0, 40)

}
