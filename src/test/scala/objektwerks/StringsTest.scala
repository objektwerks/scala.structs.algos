package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Strings.*

final class StringsTest extends AnyFunSuite with Matchers:
  test("reverse words"):
    reverseWords("scala hello") shouldBe "hello scala"

  test("countChars"):
    countChars("scala") shouldBe Map('a' -> 2, 'c' -> 1, 'l' -> 1, 's' -> 1)

  test("checkAnagrams"):
    checkAnagrams("desserts","stressed") shouldBe true
    checkAnagrams("scala", "haskell") shouldBe false

  test("checkSortedAnagrams"):
    checkSortedAnagrams("desserts","stressed") shouldBe true
    checkSortedAnagrams("scala", "haskell") shouldBe false

  test("containsBalancedParens"):
    containsBalancedParens("()") shouldBe true
    containsBalancedParens("(") shouldBe false

  test("buildListOfAllValidParens"):
    buildListOfAllValidParens(0) shouldBe List()
    buildListOfAllValidParens(1) shouldBe List("()")
    buildListOfAllValidParens(2) shouldBe List("()()", "(())")

  test("justifyText"):
    val text ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam vel urna bibendum, pharetra mi quis, imperdiet nibh. Praesent dictum odio lacus, eget commodo sem aliquam rutrum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec eget rhoncus mauris, quis vehicula mi. Quisque finibus purus non varius dictum. Pellentesque vulputate fringilla egestas. Nunc eleifend ex sed egestas cursus. Praesent molestie nisl in pretium vehicula. Vestibulum efficitur ut risus quis porta. Praesent non sem quam. Donec vitae arcu sapien. Quisque aliquet nibh in metus efficitur ullamcorper. Donec mattis dapibus nisl sed iaculis. Curabitur eu blandit enim. Fusce varius."
    justify(text, 40).nonEmpty shouldBe true