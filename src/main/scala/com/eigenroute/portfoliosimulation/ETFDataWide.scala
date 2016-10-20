package com.eigenroute.portfoliosimulation

import org.joda.time.DateTime

case class ETFDatum(
    eTFCode: ETFCode,
    xnumber: String,
    nAV: BigDecimal,
    exDividend: BigDecimal,
    quantity: BigDecimal)

case class ETFDataWide(asOfDate: DateTime, eTFDatas: Seq[ETFDatum])
