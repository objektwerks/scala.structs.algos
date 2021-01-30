package objektwerks

object AsymmetricCrypto {
  import java.security.{KeyPair, KeyPairGenerator, PrivateKey, PublicKey}
  import java.util.Base64
  import javax.crypto.Cipher

  import scala.util.Try


  def encrypt(privateKey: PrivateKey, text: String): Either[Throwable, String] =
    Try {
      val cipher = Cipher.getInstance("RSA")
      cipher.init(Cipher.ENCRYPT_MODE, privateKey)
      val encryptedBytes = cipher.doFinal( text.getBytes("UTF-8") )
      Base64.getEncoder.encodeToString(encryptedBytes)
    }.toEither

  def decrypt(publicKey: PublicKey, encryptedText: String): Either[Throwable, String] =
    Try {
      val cipher = Cipher.getInstance("RSA")
      cipher.init(Cipher.DECRYPT_MODE, publicKey)
      val decodedBytes = Base64.getDecoder.decode(encryptedText)
      val decryptedBytes = cipher.doFinal(decodedBytes)
      new String(decryptedBytes)
    }.toEither

  def generateKeyPair: Either[Throwable, KeyPair] =
    Try {
      val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
      keyPairGenerator.initialize(1024)
      keyPairGenerator.generateKeyPair
    }.toEither
}