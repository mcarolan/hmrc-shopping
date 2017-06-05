package net.mcarolan.hmrcshopping

import org.scalatest._

class CheckoutCalculatorTest extends FunSuite with Matchers {

  test("Price of an empty basket should be 0") {
    CheckoutCalculator.price(List.empty) shouldBe BigDecimal(0)
  }
  
}
