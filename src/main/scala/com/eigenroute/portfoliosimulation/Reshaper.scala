package com.eigenroute.portfoliosimulation

class Reshaper {

  def wide(eTFDatas: Seq[ETFData]): Seq[ETFDataWide] =
    eTFDatas.map(_.asOfDate).distinct.map { date =>
      val eTFDataForOneDate = eTFDatas.filter { eTFData => eTFData.asOfDate.isEqual(date) }
      ETFDataWide(date, eTFDataForOneDate.map (eTFData =>
        ETFDatum(eTFData.eTFCode, eTFData.xnumber, eTFData.nAV, eTFData.exDividend, eTFData.quantity)))
    }


}
