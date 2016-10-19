package com.eigenroute.portfoliosimulation

case class PortfolioQuantityToAcquire(
    eTFCode: ETFCode,
    quantityToAcquire:Int,
    effectivePrice: BigDecimal,
    fractionalQuantity: BigDecimal)
