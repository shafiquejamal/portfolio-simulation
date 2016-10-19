package com.eigenroute.portfoliosimulation

import java.io.File

import akka.actor.{ActorSystem, Props}
import akka.routing.SmallestMailboxPool
import com.eigenroute.portfoliosimulation.db.DevProdDBConfig

import scala.util.Try

object Main extends App {

  val dBConfig = new DevProdDBConfig()
  dBConfig.setUpAllDB()

  val investmentDurationYears: Int = Try(args(0).toInt).toOption.getOrElse(10)
  val rebalancingInterval: RebalancingInterval = RebalancingInterval.rebalancingInterval(args(1))
  val initialInvestment: BigDecimal = BigDecimal(args(2))
  val perTransactionTradingCost: BigDecimal = BigDecimal(args(3))
  val bidAskCostFractionOfNav: BigDecimal = BigDecimal(args(4))
  val maxAllowedDeviation: BigDecimal = BigDecimal(args(5))
  val portfolioDesignPath = new File(args(6))
  val outputFilePath = new File(args(7))
  val portfolioDesign = PortfolioDesign(portfolioDesignPath)
  val sortedCommonDatesETFData = new ETFDataFetcher(new ETFDAO(new DevProdDBConfig())).fetch(portfolioDesign)

  val system = ActorSystem("PortfolioSimulationActorSystem")

  val reaper = system.actorOf(Props[ReaperImpl], "reaper")

  val investmentPeriodsGenerator =
    system.actorOf(Props(classOf[InvestmentPeriodsGenerator], reaper), "investmentPeriodsGenerator")
  val routerToSimulator =
    system.actorOf(Props(classOf[PortfolioSimulator], reaper).withRouter(SmallestMailboxPool(nrOfInstances = 8)))
  val resultsWriter =
    system.actorOf(Props(classOf[ResultsWriter], reaper))

  val portfolioSimulationManager =
    system.actorOf(Props(classOf[PortfolioSimulationManager],
      reaper,
      investmentPeriodsGenerator,
      routerToSimulator,
      resultsWriter), "portfolioSimulator")

  val simulationParameters =
    SimulationParameters(
      portfolioDesign,
      sortedCommonDatesETFData,
      investmentDurationYears,
      rebalancingInterval,
      initialInvestment,
      perTransactionTradingCost,
      bidAskCostFractionOfNav,
      maxAllowedDeviation)

  portfolioSimulationManager ! simulationParameters

}