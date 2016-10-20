package com.eigenroute.portfoliosimulation.portfoliocalculation

import com.eigenroute.portfoliosimulation.ETFCode

case class PortfolioQuantityToAcquire(
    eTFCode: ETFCode,
    quantityToAcquire:Int,
    effectivePrice: BigDecimal,
    fractionalQuantity: BigDecimal)
