package objektwerks

object AsymmetricCrypto {
  import java.security.{KeyPairGenerator, PrivateKey, PublicKey}
  import java.util.Base64
  import javax.crypto.Cipher

  import scala.util.Try


  def encrypt(text: String, privateKey: PrivateKey): Either[Throwable, String] =
    Try {
      val cipher = Cipher.getInstance("RSA")
      cipher.init(Cipher.ENCRYPT_MODE, privateKey)
      val encryptedBytes = cipher.doFinal( text.getBytes("UTF-8") )
      Base64.getEncoder.encodeToString(encryptedBytes)
    }.toEither

  def decrypt(encryptedText: String, publicKey: PublicKey): Either[Throwable, String] =
    Try {
      val cipher = Cipher.getInstance("RSA")
      cipher.init(Cipher.DECRYPT_MODE, publicKey)
      val decodedBytes = Base64.getDecoder.decode(encryptedText)
      val decryptedBytes = cipher.doFinal(decodedBytes)
      new String(decryptedBytes)
    }.toEither

  def generateKeyPair: Either[Throwable, (PrivateKey, PublicKey)] =
    Try {
      val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
      keyPairGenerator.initialize(1024)
      val keyPair = keyPairGenerator.generateKeyPair
      (keyPair.getPrivate, keyPair.getPublic)
    }.toEither
}