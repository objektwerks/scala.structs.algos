package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CryptoTest extends AnyFunSuite with Matchers {
  test("encrypt > decrypt") {
    val sharedSecret = Crypto.randomByteArray.mkString
    val sharedSalt = Crypto.randomByteArray
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"

    println(s"shared secret: $sharedSecret")
    println(s"shared salt: ${sharedSalt.mkString}")
    println(s"text: $text")

    for {
      encryptedText <- Crypto.encrypt(sharedSecret, sharedSalt, text)
      decryptedText <- Crypto.decrypt(sharedSecret, sharedSalt, encryptedText)
    } yield {
      println(s"encrypted text: $encryptedText")
      println(s"decrypted text: $decryptedText")
      text shouldBe decryptedText
    }
  }
}