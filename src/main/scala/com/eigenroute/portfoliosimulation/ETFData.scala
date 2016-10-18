package com.eigenroute.portfoliosimulation

import org.joda.time.DateTime

case class ETFData(
    asOfDate: DateTime,
    eTFCode: ETFCode,
    xnumber: String,
    nAV: BigDecimal,
    exDividend: BigDecimal,
    quantity: BigDecimal)
