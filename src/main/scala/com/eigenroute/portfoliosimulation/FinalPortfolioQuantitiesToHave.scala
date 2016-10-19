package com.eigenroute.portfoliosimulation

case class FinalPortfolioQuantitiesToHave(
    quantitiesToHave: Seq[FinalPortfolioQuantityToHave],
    cashRemaining: BigDecimal,
    maxActualDeviation: BigDecimal,
    additionalQuantities: Seq[AddnlQty])
