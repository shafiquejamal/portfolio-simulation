package com.eigenroute.portfoliosimulation.portfoliocalculation

import com.eigenroute.portfoliosimulation.PortfolioSnapshot

class LiquidatedValueCalculator {

  def liquidatedValue(
      portfolioSnapshot: PortfolioSnapshot,
      bidAskCostFractionOfNAV: BigDecimal,
      perETFTradingCost: BigDecimal,
      accumulatedExDividends: BigDecimal,
      accumulatedCash: BigDecimal): BigDecimal = {

    val eTFLiquidatedValue =
      portfolioSnapshot
      .sameDateUniqueCodesETFDatas.map( eTFData => eTFData.quantity * eTFData.nAV/(1 + bidAskCostFractionOfNAV)).sum

    val totalTradingCosts = perETFTradingCost*portfolioSnapshot.sameDateUniqueCodesETFDatas.filterNot(_.quantity == 0).length

    eTFLiquidatedValue + accumulatedExDividends + accumulatedCash - totalTradingCosts

  }

}
