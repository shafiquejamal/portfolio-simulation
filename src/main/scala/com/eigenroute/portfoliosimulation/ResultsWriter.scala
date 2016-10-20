package com.eigenroute.portfoliosimulation

import org.apache.commons.io.FilenameUtils
import java.io.File

import akka.actor.ActorRef
import org.joda.time.format.DateTimeFormat

class ResultsWriter(reaper: ActorRef, file: File) extends WatchedActor(reaper) {

  override def receive = {
    case rebalancedPortfolio: RebalancedPortfolio =>
      // log.info(s"\n\n ResultsWriter: Got a result: ${rebalancedPortfolio.portfolioPerformance}")

      val datePattern: String = "yyyy-MM-dd"
      val startDate = rebalancedPortfolio.portfolioPerformance.investmentPeriod.startDate
      val endDate = rebalancedPortfolio.portfolioPerformance.investmentPeriod.endDate
      val dateTimeFormatter = DateTimeFormat.forPattern(datePattern)
      val sheetName = dateTimeFormatter.print(startDate) + "_" + dateTimeFormatter.print(endDate)

      val filePath = file.getAbsolutePath
      val filePathWithoutExtension = FilenameUtils.removeExtension(filePath)
      val extension = FilenameUtils.getExtension(filePath)
      val finalFilePath = filePathWithoutExtension + "_" + sheetName + "." + extension
      val writer = new PerformanceResultsWriter(finalFilePath, sheetName)
      writer.write(rebalancedPortfolio)
  }

}
