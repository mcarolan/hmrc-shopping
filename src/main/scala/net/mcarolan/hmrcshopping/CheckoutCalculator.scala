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

object CheckoutCalculator {

  def price(basket: List[Product]): BigDecimal =
    if (basket.isEmpty)
      BigDecimal(0)
    else
      basket.head.unitPrice * basket.size

}