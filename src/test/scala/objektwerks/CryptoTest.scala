package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CryptoTest extends AnyFunSuite with Matchers {
  test("encrypt > decrypt") {
    val sharedSecret = "thi"
    val sharedSalt = "ssshhhhhhhhhhh!!!!"
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"
    val encryptedText = Crypto.encrypt(sharedSecret, sharedSalt, text).getOrElse("Encryption failed!")
    val decryptedText = Crypto.decrypt(sharedSecret, sharedSalt, encryptedText).getOrElse("Decription failed!")
    println(s"text: $text")
    println(s"encrypted text: $encryptedText")
    println(s"decrypted text: $decryptedText")
  }
}