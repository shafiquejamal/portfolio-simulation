package com.eigenroute.portfoliosimulation

case class ETFSelection(eTFCode: ETFCode, desiredWeight: BigDecimal) {
  require(desiredWeight >= 0 & desiredWeight <= 1)
}
