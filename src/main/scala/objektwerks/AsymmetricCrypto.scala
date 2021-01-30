package objektwerks

object AsymmetricCrypto {
  import java.security.{KeyPairGenerator, PrivateKey, PublicKey}

  import scala.util.Try

  def generateKeyPair: Either[Throwable, (PrivateKey, PublicKey)] =
    Try {
      val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
      keyPairGenerator.initialize(1024)
      val keyPair = keyPairGenerator.generateKeyPair
      (keyPair.getPrivate, keyPair.getPublic)
    }.toEither
}