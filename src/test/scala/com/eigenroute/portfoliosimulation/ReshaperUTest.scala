package com.eigenroute.portfoliosimulation

import org.scalatest.{FlatSpecLike, ShouldMatchers}

class ReshaperUTest extends FlatSpecLike with ShouldMatchers with PortfolioFixture {

  "The reshaper" should "convert long to wide" in new InvestmentFixture {

    val expected = Seq(
      ETFDataWide(startDatePlus18months,
        Seq(ETFDatum(eTFA, "1", 40, 0, 154), ETFDatum(eTFB, "1", 60, 0, 217), ETFDatum(eTFC, "1", 80, 0, 25),
            ETFDatum(eTFD, "1", 100, 0, 50))),
      ETFDataWide(startDatePlus24months,
        Seq(ETFDatum(eTFA, "1", 150, 0, 94), ETFDatum(eTFB, "1", 120, 0, 217), ETFDatum(eTFC, "1", 90, 0, 64),
            ETFDatum(eTFD, "1", 60, 0, 141))),
      ETFDataWide(startDatePlus24monthsMinus1Day,
        Seq(ETFDatum(eTFA, "1", 85, 0, 154), ETFDatum(eTFB, "1", 125, 0, 217), ETFDatum(eTFC, "1", 165, 0, 25),
            ETFDatum(eTFD, "1", 205, 0, 50))),
      ETFDataWide(startDatePlus30months,
        Seq(ETFDatum(eTFA, "1", 110, 0, 94), ETFDatum(eTFB, "1", 90, 0, 301), ETFDatum(eTFC, "1", 20, 0, 272),
            ETFDatum(eTFD, "1", 140, 0, 57))),
      ETFDataWide(startDatePlus36months,
        Seq(ETFDatum(eTFA, "1", 75, 0, 94), ETFDatum(eTFB, "1", 115, 0, 301), ETFDatum(eTFC, "1", 155, 1.75, 272),
            ETFDatum(eTFD, "1", 195, 0, 57))),
      ETFDataWide(startDatePlus36monthsMinus1Day,
        Seq(ETFDatum(eTFA, "1", 80, 0, 94), ETFDatum(eTFB, "1", 120, 0, 301), ETFDatum(eTFC, "1", 160, 1.75, 272),
            ETFDatum(eTFD, "1", 200, 0, 57)))
    )
    val actual = new Reshaper().wide(expectedRebalancedPortfolioSemiAnnuallyOnly)

    actual should contain theSameElementsInOrderAs expected
  }

}
