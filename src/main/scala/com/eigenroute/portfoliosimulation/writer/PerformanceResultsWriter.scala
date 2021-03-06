package com.eigenroute.portfoliosimulation.writer

import java.io.FileOutputStream

import com.eigenroute.portfoliosimulation.RebalancedPortfolio
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class PerformanceResultsWriter(filePath: String, sheetName: String) {

  def write(rebalancedPortfolio: RebalancedPortfolio): Unit =
    writer(new PerformanceWorkbookCreator().create(rebalancedPortfolio, sheetName))

  def writeWide(rebalancedPortfolio: RebalancedPortfolio): Unit =
    writer(new PerformanceWorkbookCreator().createWide(rebalancedPortfolio, sheetName))

  def writer(wb: XSSFWorkbook): Unit = {
    val fileOutputStream = new FileOutputStream(filePath)
    wb.write(fileOutputStream)
    fileOutputStream.close()
  }

}
