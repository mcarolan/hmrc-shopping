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

  def price(basket: List[Product]): BigDecimal =
    basket.foldLeft(BigDecimal(0)){ case (acc, product) =>
      acc + product.unitPrice
    }

  def reduceBasketWithOffers(basket: List[Product], offers: List[Offer]): List[Product] =
    ???

}