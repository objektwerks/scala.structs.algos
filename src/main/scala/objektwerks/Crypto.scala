package objektwerks

object Crypto {
  import javax.crypto.Cipher
  import javax.crypto.SecretKeyFactory
  import javax.crypto.spec.IvParameterSpec
  import javax.crypto.spec.PBEKeySpec
  import javax.crypto.spec.SecretKeySpec
  import java.util.Base64

  import scala.util.Try

  def encrypt(encryptionTarget: String,
              sharedSecret: String,
              sharedSalt: String): Option[String] =
    Try {
      val iv = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      val ivParamSpec = new IvParameterSpec(iv)
      val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
      val keySpec = new PBEKeySpec(sharedSecret.toCharArray, sharedSalt.getBytes, 65536, 256)
      val secret = keyFactory.generateSecret(keySpec)
      val secretKey = new SecretKeySpec(secret.getEncoded, "AES")
      val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParamSpec)
      val encryptedBytes = cipher.doFinal(encryptionTarget.getBytes("UTF-8"))
      Base64.getEncoder.encodeToString(encryptedBytes).mkString
    }.toOption
}