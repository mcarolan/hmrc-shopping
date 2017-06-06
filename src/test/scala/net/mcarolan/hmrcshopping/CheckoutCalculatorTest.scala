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
  val orangeOffer = Offer(Orange, quantityRequired = 3, numberFree = 1)

  test("Reduce basket with 2 Apples only and bogof offer yields 1 apple") {
    CheckoutCalculator.reduceBasketWithOffers(List(Apple, Apple), List(appleOffer)) shouldBe List(Apple)
  }

  test("Reduce basket with 3 Apples only and bogof offer yields 2 apples") {
    CheckoutCalculator.reduceBasketWithOffers(List(Apple, Apple, Apple), List(appleOffer)) shouldBe List(Apple, Apple)
  }

  test("Reduce basket with 2 Apples and 1 Orange, with bogof for Apples yields 1 Apple 1 Orange") {
    val result = CheckoutCalculator.reduceBasketWithOffers(List(Apple, Orange, Apple), List(appleOffer))
    result should contain theSameElementsAs List(Apple, Orange)
  }

  test("Reduce basket with 2 Apples, 3 Oranges with bogof Apples, 3 for 2 Oranges yields 1 Apple 2 Oranges") {
    val basket = List(Apple, Orange, Apple, Orange, Orange)
    val result = CheckoutCalculator.reduceBasketWithOffers(basket, List(appleOffer, orangeOffer))
    result should contain theSameElementsAs List(Apple, Orange, Orange)
  }

  test("Basket reduction with multiple applications of offers") {
    val basket = List(Apple, Orange, Apple, Orange, Apple, Orange, Orange, Apple, Apple)
    val result = CheckoutCalculator.reduceBasketWithOffers(basket, List(appleOffer, orangeOffer))
    result should contain theSameElementsAs List(Apple, Apple, Apple, Orange, Orange, Orange)
  }

}
