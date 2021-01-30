package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SymmetricCryptoTest extends AnyFunSuite with Matchers {
  test("encrypt > decrypt") {
    val sharedSecret = SymmetricCrypto.secureRandomByteArray.mkString
    val sharedSalt = SymmetricCrypto.secureRandomByteArray
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"

    println(s"shared secret: $sharedSecret")
    println(s"shared salt: ${sharedSalt.mkString}")
    println(s"text: $text")

    for {
      encryptedText <- SymmetricCrypto.encrypt(sharedSecret, sharedSalt, text)
      decryptedText <- SymmetricCrypto.decrypt(sharedSecret, sharedSalt, encryptedText)
    } yield {
      println(s"encrypted text: $encryptedText")
      println(s"decrypted text: $decryptedText")
      text shouldBe decryptedText
    }
  }
}