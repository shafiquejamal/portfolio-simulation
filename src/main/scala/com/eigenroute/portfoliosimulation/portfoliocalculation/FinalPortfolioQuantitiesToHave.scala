package com.eigenroute.portfoliosimulation.portfoliocalculation

import com.eigenroute.portfoliosimulation.AddnlQty

case class FinalPortfolioQuantitiesToHave(
    quantitiesToHave: Seq[FinalPortfolioQuantityToHave],
    cashRemaining: BigDecimal,
    maxActualDeviation: BigDecimal,
    additionalQuantities: Seq[AddnlQty])
