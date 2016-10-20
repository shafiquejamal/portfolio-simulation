package com.eigenroute.portfoliosimulation.portfoliocalculation

import com.eigenroute.portfoliosimulation.{PortfolioDesign, PortfolioSnapshot}

class WeightDifferenceCalculator extends PortfolioValueCalculation {

  def weightDifferences(
      portfolioDesign: PortfolioDesign,
      portfolioSnapshot: PortfolioSnapshot):Seq[PortfolioWeightDifference] = {

    val portfolioVal = portfolioValueETFsOnly(portfolioSnapshot)

    portfolioDesign.eTFSelections.map { eTFDATA =>
      val eTFCode = eTFDATA.eTFCode
      val desiredWeight = eTFDATA.desiredWeight
      val actualWeight: BigDecimal =
        if (portfolioVal != BigDecimal(0))
          actualValue(portfolioSnapshot, eTFCode) / portfolioVal
        else
          0d
      PortfolioWeightDifference(eTFCode, desiredWeight - actualWeight)
    }

  }

}
