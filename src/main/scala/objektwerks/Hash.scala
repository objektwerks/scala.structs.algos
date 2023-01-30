package objektwerks

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object Hash:
  type Hash = String

  def sha3256(value: String): Hash =
    val digest = MessageDigest.getInstance("SHA3-256")
    val hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8))
    bytesToHexString(hashBytes)

  def bytesToHexString(hashBytes: Array[Byte]): String =
    val hexStringBuilder = new StringBuilder(2 * hashBytes.length)
    for 
      i <- hashBytes.indices
    do
      val hexString = Integer.toHexString(0xff & hashBytes(i))
      if (hexString.length == 1) hexStringBuilder.append('0')
      hexStringBuilder.append(hexString)
    hexStringBuilder.toString