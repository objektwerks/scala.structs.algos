package objektwerks

object Crypto {
  import javax.crypto.Cipher
  import javax.crypto.SecretKeyFactory
  import javax.crypto.spec.IvParameterSpec
  import javax.crypto.spec.PBEKeySpec
  import javax.crypto.spec.SecretKeySpec
  import java.util.Base64

  import scala.util.Try

  def encrypt(value: String,
              secret: String,
              salt: String): Option[String] =
    Try {
      val iv = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      val ivspec = new IvParameterSpec(iv)
      val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
      val spec = new PBEKeySpec(secret.toCharArray, salt.getBytes, 65536, 256)
      val tmp = factory.generateSecret(spec)
      val secretKey = new SecretKeySpec(tmp.getEncoded, "AES")
      val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec)
      Base64.getEncoder.encodeToString(cipher.doFinal(value.getBytes("UTF-8"))).mkString
    }.toOption
}