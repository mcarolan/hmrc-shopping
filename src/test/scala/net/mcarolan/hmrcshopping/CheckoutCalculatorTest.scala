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

  test("Price of basket with mixed items should be sum of unit prices") {
    val numberOfOranges = 3
    val numberOfApples = 4
    val basket = List.fill(numberOfOranges)(Orange) ++ List.fill(numberOfApples)(Apple)
    val expectedPrice = Orange.unitPrice * numberOfOranges + Apple.unitPrice * numberOfApples
    CheckoutCalculator.price(basket) shouldBe expectedPrice
  }

  val appleOffer = Offer(Apple, quantityRequired = 2, numberFree = 1)

  test("Reduce basket with 2 Apples only and bogof offer yields 1 apple") {
    CheckoutCalculator.reduceBasketWithOffers(List(Apple, Apple), List(appleOffer)) shouldBe List(Apple)
  }

}
