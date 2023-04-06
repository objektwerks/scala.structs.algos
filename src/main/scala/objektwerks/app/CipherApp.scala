package objektwerks.app

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane

object CipherApp extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View().scene
      title = "Ciphers"
      minWidth = 800
      minHeight = 600
    stage.show()

class View:
  val borderPane = new BorderPane:
    prefWidth = 800
    prefHeight = 600
    padding = Insets(6)
  val scene = new Scene:
    root = borderPane