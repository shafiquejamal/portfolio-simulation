package com.eigenroute.portfoliosimulation.reader

import com.eigenroute.portfoliosimulation.portfoliocalculation.OverlappingDatesCalculator
import com.eigenroute.portfoliosimulation.{ETFData, PortfolioDesign}

class ETFDataFetcher(eTFDAO: ETFDAO) {

  def fetch(portfolioDesign: PortfolioDesign): Seq[ETFData] = {
    val rawETFData = eTFDAO.by(portfolioDesign.eTFSelections.map(_.eTFCode))
      .sortWith( (eTFData1, eTFData2) => eTFData1.asOfDate.isBefore(eTFData2.asOfDate) )
    new OverlappingDatesCalculator(portfolioDesign).eTFDataOnlyWithEntriesHavingOverlappingDates(rawETFData)
  }

}
