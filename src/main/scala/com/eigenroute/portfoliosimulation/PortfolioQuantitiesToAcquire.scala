package com.eigenroute.portfoliosimulation

case class PortfolioQuantitiesToAcquire(quantitiesToAcquire: Seq[PortfolioQuantityToAcquire]) {

  def +(additionalQuantities:Seq[AddnlQty]):PortfolioQuantitiesToAcquire = {
    PortfolioQuantitiesToAcquire(this.quantitiesToAcquire.map { quantityToHave =>
      val quantityToAdd:Int = additionalQuantities.find(_.eTFCode == quantityToHave.eTFCode).map(_.quantity).getOrElse(0)
      quantityToHave.copy(quantityToAcquire = quantityToHave.quantityToAcquire + quantityToAdd)
    })
  }

}
