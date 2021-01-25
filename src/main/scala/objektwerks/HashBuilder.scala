package objektwerks

object HashBuilder {
  import java.nio.charset.StandardCharsets
  import java.security.MessageDigest

  def hash(value: String): String = {
    val digest = MessageDigest.getInstance("SHA3-256")
    val hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8))
    bytesToHexString(hashBytes)
  }

  private def bytesToHexString(hashBytes: Array[Byte]): String = {
    val hexStringBuilder = new StringBuilder(2 * hashBytes.length)
    for (i <- hashBytes.indices) {
      val hexString = Integer.toHexString(0xff & hashBytes(i))
      if (hexString.length == 1) hexStringBuilder.append('0')
      hexStringBuilder.append(hexString)
    }
    hexStringBuilder.toString
  }
}