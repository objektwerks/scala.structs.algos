package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AsymmetricCryptoTest extends AnyFunSuite with Matchers {
  test("encrypt > decrypt") {
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"
    println(s"text: $text")

    for {
      keyPair <- AsymmetricCrypto.generateKeyPair
      encryptedText <- AsymmetricCrypto.encrypt(keyPair.getPublic, text)
      decryptedText <- AsymmetricCrypto.decrypt(keyPair.getPrivate, encryptedText)
    } yield {
      println(s"asymmetric encrypted text: $encryptedText")
      println(s"asymmetric decrypted text: $decryptedText")
      text shouldBe decryptedText
    }
  }
}