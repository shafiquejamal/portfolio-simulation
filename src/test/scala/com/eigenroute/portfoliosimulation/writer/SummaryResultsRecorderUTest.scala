package com.eigenroute.portfoliosimulation.writer

import com.eigenroute.portfoliosimulation._
import com.eigenroute.portfoliosimulation.investmentperiod.InvestmentPeriod
import com.eigenroute.portfoliosimulation.portfoliocalculation.PortfolioPerformance
import org.scalatest._

class SummaryResultsRecorderUTest extends FlatSpec with ShouldMatchers with PortfolioFixture {

  "The results recorder" should "create a workbook with the parameters in the first sheet and the performances results in " +
  "the second sheet" in {

    val simulationParameters =
      SimulationParameters(portfolioDesign, Seq(), 6, RebalancingInterval.Monthly, 234000, 9.99, 0.0014, 0.04,
        isWriteHistoricalRebalanced = false)

    val performances: Seq[PortfolioPerformance] =
      Seq(
        PortfolioPerformance(InvestmentPeriod(now, now.plusYears(6)), 0.12),
        PortfolioPerformance(InvestmentPeriod(now.plusDays(1), now.plusYears(6).plusDays(1)), 0.12),
        PortfolioPerformance(InvestmentPeriod(now.plusDays(2), now.plusYears(6).plusDays(2)), 1.52),
        PortfolioPerformance(InvestmentPeriod(now.plusDays(3), now.plusYears(6).plusDays(4)), 2.03)
      )
    val recorder = new SummaryResultsRecorder(simulationParameters, performances)

    val wb = recorder.write("yy,M,d")

    wb.getSheetAt(0).getRow(2).getCell(0).getStringCellValue shouldEqual "Initial Investment"
    wb.getSheetAt(0).getRow(2).getCell(1).getNumericCellValue shouldEqual 234000d

    wb.getSheetAt(1).getRow(3).getCell(0).getStringCellValue shouldEqual "16,1,3"
    wb.getSheetAt(1).getRow(3).getCell(1).getStringCellValue shouldEqual "22,1,3"
    wb.getSheetAt(1).getRow(3).getCell(2).getNumericCellValue shouldEqual 1.52

    recorder.write().getSheetAt(1).getRow(3).getCell(1).getStringCellValue shouldEqual "2022-01-03"
  }

}
