package com.eigenroute.portfoliosimulation

import java.io.{File, FileOutputStream}

import akka.actor.ActorRef
import org.apache.commons.io.FilenameUtils
import org.joda.time.format.DateTimeFormat

class ResultsWriter(reaper: ActorRef, file: File) extends WatchedActor(reaper) {

  val filePath = file.getAbsolutePath
  var portfolioPerformances: Seq[PortfolioPerformance] = Seq.empty

  override def receive = {

    case rebalancedPortfolio: RebalancedPortfolio =>
      log.info(s"\n\n ResultsWriter: RebalancedPortfolio: ${rebalancedPortfolio.portfolioPerformance}")

      val datePattern: String = "yyyy-MM-dd"
      val startDate = rebalancedPortfolio.portfolioPerformance.investmentPeriod.startDate
      val endDate = rebalancedPortfolio.portfolioPerformance.investmentPeriod.endDate
      val dateTimeFormatter = DateTimeFormat.forPattern(datePattern)
      val sheetName = dateTimeFormatter.print(startDate) + "_" + dateTimeFormatter.print(endDate)

      val filePathWithoutExtension = FilenameUtils.removeExtension(filePath)
      val extension = FilenameUtils.getExtension(filePath)
      val finalFilePath = filePathWithoutExtension + "_" + sheetName + "." + extension
      val writer = new PerformanceResultsWriter(finalFilePath, sheetName)
      writer.writeWide(rebalancedPortfolio)

    case portfolioPerformance: PortfolioPerformance =>
      portfolioPerformances = portfolioPerformances :+ portfolioPerformance

    case simulationParameters: SimulationParameters =>
      log.info(s"\n\n ResultsWriter: Got a summary: ${simulationParameters.portfolioDesign}")
      val summaryWorkbook =
        new SummaryResultsRecorder(simulationParameters, portfolioPerformances.sortBy( performance =>
          performance.investmentPeriod.startDate.getMillis)).write()
      val fileOutputStream = new FileOutputStream(file)
      summaryWorkbook.write(fileOutputStream)
      fileOutputStream.close()
  }

}
