package com.eigenroute.portfoliosimulation

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.joda.time.format.DateTimeFormat

class PerformanceWorkbookCreator {

  def create(
      rebalancedPortfolio: RebalancedPortfolio,
      sheetName: String,
      datePattern: String = "yyyy-MM-dd"): XSSFWorkbook = {

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

  def createWide(
      rebalancedPortfolio: RebalancedPortfolio,
      sheetName: String,
      datePattern: String = "yyyy-MM-dd"): XSSFWorkbook = {

    val wb = new XSSFWorkbook()
    val sheet = wb.createSheet(sheetName)
    val dateTimeFormatter = DateTimeFormat.forPattern(datePattern)

    val row = sheet.createRow(0)
    row.createCell(0).setCellValue("as-of-date")
    row.createCell(1).setCellValue("Code")
    row.createCell(2).setCellValue("NAV")
    row.createCell(3).setCellValue("Quantity")
    row.createCell(4).setCellValue("ExDividend")

    new Reshaper().wide(rebalancedPortfolio.rebalancedETFData)
    .sortBy(_.asOfDate.getMillis).zipWithIndex.foreach { case (eTFDataWide, index) =>
      val row = sheet.createRow(index + 1)
      val asOfDate = dateTimeFormatter.print(eTFDataWide.asOfDate)
      row.createCell(0).setCellValue(asOfDate)
      eTFDataWide.eTFDatas.zipWithIndex.foreach { case (eTFDatum, datumIndex) =>
       row.createCell(1 + datumIndex*4).setCellValue(eTFDatum.eTFCode.code)
       row.createCell(2 + datumIndex*4).setCellValue(eTFDatum.nAV.toDouble)
       row.createCell(3 + datumIndex*4).setCellValue(eTFDatum.quantity.toDouble)
       row.createCell(4 + datumIndex*4).setCellValue(eTFDatum.exDividend.toDouble)
      }
    }

    wb
  }

}
