package objektwerks.app

import scalafx.application.JFXApp3
import scalafx.scene.Scene

object CipherApp extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = new Scene()
      title = "Ciphers"
      minWidth = 800
      minHeight = 600
    stage.show()