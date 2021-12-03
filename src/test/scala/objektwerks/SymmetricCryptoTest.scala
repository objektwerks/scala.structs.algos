package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SymmetricCryptoTest extends AnyFunSuite with Matchers:
  test("encrypt > decrypt") {
    val sharedSecret = SymmetricCrypto.secureRandomByteArray.mkString
    val sharedSalt = SymmetricCrypto.secureRandomByteArray
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"

    println(s"symmetric shared secret: $sharedSecret")
    println(s"symmetric shared salt: ${sharedSalt.mkString}")
    println(s"symmetric text: $text")

    for
      encryptedText <- SymmetricCrypto.encrypt(sharedSecret, sharedSalt, text)
      decryptedText <- SymmetricCrypto.decrypt(sharedSecret, sharedSalt, encryptedText)
    yield
      println(s"symmetric encrypted text: $encryptedText")
      println(s"symmetric decrypted text: $decryptedText")
      text shouldBe decryptedText
  }