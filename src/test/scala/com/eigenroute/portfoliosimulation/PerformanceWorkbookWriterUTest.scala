package com.eigenroute.portfoliosimulation

import org.scalatest.{FlatSpecLike, ShouldMatchers}

class PerformanceWorkbookWriterUTest extends FlatSpecLike with ShouldMatchers with PortfolioFixture {

  "The workbook writer" should "write the rebalanced portfolio data to a sheet" in new PortfolioFiles
  with InvestmentFixture {

    val write = new PerformanceWorkbookWriter()

    val wb = write.write(rebalancedPortfolio, "2010-01-01 to 2015-01-01")

    val sheet = wb.getSheetAt(0)
    sheet.getSheetName shouldEqual "2010-01-01 to 2015-01-01"
    sheet.getRow(43).getCell(0).getStringCellValue shouldEqual "2011-01-02"
    sheet.getRow(43).getCell(1).getStringCellValue shouldEqual "DDD"
    sheet.getRow(43).getCell(2).getStringCellValue shouldEqual "1"
    sheet.getRow(43).getCell(3).getNumericCellValue shouldEqual 0d
    sheet.getRow(43).getCell(4).getNumericCellValue shouldEqual 565d
    sheet.getRow(43).getCell(5).getNumericCellValue shouldEqual 47d

  }

}
