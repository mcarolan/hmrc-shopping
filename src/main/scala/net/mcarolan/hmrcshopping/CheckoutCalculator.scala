package net.mcarolan.hmrcshopping

sealed trait Product {
  def unitPrice: BigDecimal
}

case object Apple extends Product {
  override val unitPrice: BigDecimal = BigDecimal("0.6")
}

case object Orange extends Product {
  override val unitPrice: BigDecimal = BigDecimal("0.25")
}

case class Offer(product: Product, quantityRequired: Int, numberFree: Int)

object CheckoutCalculator {

  def priceWithOffers(basket: List[Product], offers: List[Offer]): BigDecimal = {
    val productQuantity: Map[Product, Int] = basket.groupBy(identity).mapValues(_.size)

    productQuantity.map { case (product, quantity) =>
      val offerOpt = offers.find(_.product == product)
      val numberFree = offerOpt.fold(0) { offer =>
        quantity / offer.quantityRequired * offer.numberFree
      }

      (quantity + numberFree * -1) * product.unitPrice
    }.sum
  }

}