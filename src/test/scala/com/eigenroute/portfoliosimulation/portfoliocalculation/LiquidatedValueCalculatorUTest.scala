package com.eigenroute.portfoliosimulation.portfoliocalculation

import com.eigenroute.portfoliosimulation.{ETFData, PortfolioFixture, PortfolioSnapshot}
import org.scalatest._

class LiquidatedValueCalculatorUTest extends FlatSpec with ShouldMatchers with PortfolioFixture {

  "The calculator" should "calculate the value of the portfolio under the condition that it is liquidated to cash" in  {
    val bidAskCostFractionOfNAV = 0.00123
    val perETFTradingCost = 12

    val portfolioData: Seq[ETFData] =
      Seq(
        ETFData(null, eTFA, "1", 30, 2, 10),
        ETFData(null, eTFB, "1", 90, 4, 5),
        ETFData(null, eTFC, "1", 25, 45, 2),
        ETFData(null, eTFD, "1", 73, 95, 1)
      )
    val portfolioSnapshot = PortfolioSnapshot(portfolioData)
    val expectedLiquidatedValue =
      BigDecimal(73*1 + 25*2 + 90*5 + 30*10)/(1 + bidAskCostFractionOfNAV) + 2 + 4 + 45 + 95 + 25 + 50 + 75 + 150 -
      perETFTradingCost*4

    val actual =
      new LiquidatedValueCalculator()
      .liquidatedValue(portfolioSnapshot, bidAskCostFractionOfNAV, perETFTradingCost, 2 + 4 + 45 + 95, 25 + 50 + 75 + 150)

    round(actual, 31) shouldEqual round(expectedLiquidatedValue, 31)
  }

  it should "not include the ETF trading cost of zero quantity ETFs" in {
    val bidAskCostFractionOfNAV = 0.00992
    val perETFTradingCost = 15

    val portfolioData: Seq[ETFData] =
      Seq(
        ETFData(null, eTFA, "1", 30, 2, 10),
        ETFData(null, eTFB, "1", 90, 4, 0),
        ETFData(null, eTFC, "1", 25, 45, 2),
        ETFData(null, eTFD, "1", 73, 95, 1)
      )
    val portfolioSnapshot = PortfolioSnapshot(portfolioData)
    val expectedLiquidatedValue =
      BigDecimal(73*1 + 25*2 + 30*10)/(1 + bidAskCostFractionOfNAV) + 2 + 4 + 45 + 95 + 25 + 50 + 75 + 150 -
      perETFTradingCost*3

    val actual =
      new LiquidatedValueCalculator()
      .liquidatedValue(portfolioSnapshot, bidAskCostFractionOfNAV, perETFTradingCost, 2 + 4 + 45 + 95, 25 + 50 + 75 + 150)

    round(actual, 31) shouldEqual round(expectedLiquidatedValue, 31)
  }

}

