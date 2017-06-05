package net.mcarolan.hmrcshopping

import org.scalatest._

class CheckoutCalculatorTest extends FunSuite with Matchers {

  test("Price of an empty basket should be 0") {
    CheckoutCalculator.price(List.empty) shouldBe BigDecimal(0)
  }

  test("Price of a basket with a single item should be item's unit price") {
    Set(Orange, Apple).foreach { fruit =>
      withClue(fruit) {
        CheckoutCalculator.price(List(fruit)) shouldBe fruit.unitPrice
      }
    }
  }

  test("Price of basket with all items of one price should be unit price * basket size") {
    Set(Orange, Apple).foreach { fruit =>
      withClue(fruit) {
        CheckoutCalculator.price(List.fill(5)(fruit)) shouldBe fruit.unitPrice * 5
      }
    }
  }

}
