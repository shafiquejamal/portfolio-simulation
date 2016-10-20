package com.eigenroute.portfoliosimulation

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.scalatest._

class PerformanceResultsWriterUTest extends FlatSpec with ShouldMatchers with PortfolioFixture {

  "The performance results writer" should "store the rebalanced portfolio data in a workbook - long format" in
  new PortfolioFiles with InvestmentFixture {

    tempOutputFile.delete()

    val writer = new PerformanceResultsWriter(tempOutputFile.getPath, "2010-01-01 to 2015-01-01")
    writer.write(rebalancedPortfolio)

    val writtenWorkbook = new XSSFWorkbook(tempOutputFile)

    val sheet = writtenWorkbook.getSheetAt(0)
    sheet.getSheetName shouldEqual "2010-01-01 to 2015-01-01"
    sheet.getRow(43).getCell(0).getStringCellValue shouldEqual "2011-01-02"
    sheet.getRow(43).getCell(1).getStringCellValue shouldEqual "DDD"
    sheet.getRow(43).getCell(2).getStringCellValue shouldEqual "1"
    sheet.getRow(43).getCell(3).getNumericCellValue shouldEqual 0d
    sheet.getRow(43).getCell(4).getNumericCellValue shouldEqual 565d
    sheet.getRow(43).getCell(5).getNumericCellValue shouldEqual 47d

  }

  it should "store the rebalanced portfolio data in a workbook - wide format" in new PortfolioFiles with InvestmentFixture {

    tempOutputFile.delete()

    val writer = new PerformanceResultsWriter(tempOutputFile.getPath, "2010-01-01 to 2015-01-01")
    writer.writeWide(rebalancedPortfolio)

    val writtenWorkbook = new XSSFWorkbook(tempOutputFile)

    val sheet = writtenWorkbook.getSheetAt(0)
    sheet.getSheetName shouldEqual "2010-01-01 to 2015-01-01"
    sheet.getRow(11).getCell(0).getStringCellValue shouldEqual "2011-01-02"
    sheet.getRow(11).getCell(13).getStringCellValue shouldEqual "DDD"
    sheet.getRow(11).getCell(16).getNumericCellValue shouldEqual 0d
    sheet.getRow(11).getCell(14).getNumericCellValue shouldEqual 565d
    sheet.getRow(11).getCell(15).getNumericCellValue shouldEqual 47d

  }
}
