package objektwerks

import java.security.SecureRandom
import java.util.Base64

import javax.crypto.spec.{IvParameterSpec, PBEKeySpec, SecretKeySpec}
import javax.crypto.{Cipher, SecretKeyFactory}

import scala.util.Try

object SymmetricCrypto:
  def encrypt(sharedSecret: String,
              sharedSalt: Array[Byte],
              text: String): Either[Throwable, String] =
    Try {
      val keySpec = new PBEKeySpec(sharedSecret.toCharArray, sharedSalt, 65536, 256)
      val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
      val secretKey = secretKeyFactory.generateSecret(keySpec)
      val secretKeySpec = new SecretKeySpec(secretKey.getEncoded, "AES")
      val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
      val buffer = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      val ivParamSpec = new IvParameterSpec(buffer)
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParamSpec)
      val encryptedBytes = cipher.doFinal( text.getBytes("UTF-8") )
      Base64.getEncoder.encodeToString(encryptedBytes)
    }.toEither

  def decrypt(sharedSecret: String,
              sharedSalt: Array[Byte],
              encryptedText: String): Either[Throwable, String] =
    Try {
      val keySpec = new PBEKeySpec(sharedSecret.toCharArray, sharedSalt, 65536, 256)
      val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
      val secretKey = secretKeyFactory.generateSecret(keySpec)
      val secretKeySpec = new SecretKeySpec(secretKey.getEncoded, "AES")
      val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
      val buffer = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      val ivParamSpec = new IvParameterSpec(buffer)
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParamSpec)
      val decodedBytes = Base64.getDecoder.decode(encryptedText)
      val decryptedBytes = cipher.doFinal(decodedBytes)
      new String(decryptedBytes)
    }.toEither

  def secureRandomByteArray: Array[Byte] =
    val random = new SecureRandom()
    val bytes = new Array[Byte](16)
    random.nextBytes(bytes)
    bytes