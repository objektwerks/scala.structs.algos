package objektwerks

import java.sql.Timestamp
import java.time.Instant

trait Entity extends Product with Serializable {
  val timestamp = Timestamp.from(Instant.now).getTime
}