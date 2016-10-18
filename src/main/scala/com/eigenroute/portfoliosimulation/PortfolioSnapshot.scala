package com.eigenroute.portfoliosimulation

case class PortfolioSnapshot(sameDateUniqueCodesETFDatas: Seq[ETFData]) {
  require(sameDateUniqueCodesETFDatas.map(_.eTFCode.code).distinct.size == sameDateUniqueCodesETFDatas.size)
}
