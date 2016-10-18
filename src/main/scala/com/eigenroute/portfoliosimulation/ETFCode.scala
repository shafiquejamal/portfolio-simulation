package com.eigenroute.portfoliosimulation

case class ETFCode(code: String) {
  require(code.length == 3)
}