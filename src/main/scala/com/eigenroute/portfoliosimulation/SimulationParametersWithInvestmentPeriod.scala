package com.eigenroute.portfoliosimulation

import com.eigenroute.portfoliosimulation.investmentperiod.InvestmentPeriod

case class SimulationParametersWithInvestmentPeriod(
    investmentPeriod: InvestmentPeriod,
    simulationParameters: SimulationParameters)
