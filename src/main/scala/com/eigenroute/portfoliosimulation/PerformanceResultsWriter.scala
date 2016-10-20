package com.eigenroute.portfoliosimulation

import java.io.FileOutputStream

import org.apache.poi.xssf.usermodel.XSSFWorkbook

class PerformanceResultsWriter(filePath: String, sheetName: String) {

  def write(rebalancedPortfolio: RebalancedPortfolio): Unit = {
    val writtenWorkbook: XSSFWorkbook = new PerformanceWorkbookWriter().write(rebalancedPortfolio, sheetName)
    val fileOutputStream = new FileOutputStream(filePath)
    writtenWorkbook.write(fileOutputStream)
    fileOutputStream.close()
  }

}
