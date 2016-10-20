package com.eigenroute.portfoliosimulation

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.joda.time.format.DateTimeFormat

class PerformanceWorkbookWriter {

  def write(rebalancedPortfolio: RebalancedPortfolio, sheetName: String, datePattern: String = "yyyy-MM-dd"): XSSFWorkbook = {
    val wb = new XSSFWorkbook()
    val sheet = wb.createSheet(sheetName)
    val dateTimeFormatter = DateTimeFormat.forPattern(datePattern)

    rebalancedPortfolio.rebalancedETFData.zipWithIndex.foreach { case (eTFData, index) =>
      val row = sheet.createRow(index)
      val asOfDate = dateTimeFormatter.print(eTFData.asOfDate)
      row.createCell(0).setCellValue(asOfDate)
      row.createCell(1).setCellValue(eTFData.eTFCode.code)
      row.createCell(2).setCellValue(eTFData.xnumber)
      row.createCell(3).setCellValue(eTFData.exDividend.toDouble)
      row.createCell(4).setCellValue(eTFData.nAV.toDouble)
      row.createCell(5).setCellValue(eTFData.quantity.toDouble)
    }

    wb
  }
}
