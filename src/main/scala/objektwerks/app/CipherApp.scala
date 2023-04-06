package objektwerks.app

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.layout.BorderPane

object CipherApp extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View.scene
      title = "Ciphers"
      minWidth = View.width
      minHeight = View.height
      icons.add( new Image(Image.getClass.getResourceAsStream("/cipher.png")) )
    stage.show()

object View:
  val width = 800
  val height = 600

  val borderPane = new BorderPane:
    prefWidth = View.width
    prefHeight = View.height
    padding = Insets(6)

  // borderPane.top = TODO
  // borderPane.center = TODO

  val scene = new Scene:
    root = borderPane
    stylesheets = List("/style.css")