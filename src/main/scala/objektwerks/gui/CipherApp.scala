package objektwerks.gui

import scalafx.application.JFXApp3
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.layout.{BorderPane, HBox, VBox}

import objektwerks.Ciphers.*
import scalafx.scene.control.TableView
import scalafx.scene.control.TableColumn
import scalafx.scene.layout.Priority

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

  borderPane.top = EncodingsPane()
  borderPane.center = TextPane()

  val scene = new Scene:
    root = borderPane
    stylesheets = List("/style.css")

object Model:
  val encodings = ObservableBuffer[Encodings]()

final class EncodingsPane extends VBox:
  spacing = 6
  padding = Insets(6)

  val tableView = new TableView[Encodings]():
    columns ++= List(
      new TableColumn[Encodings, Int]:
        text = "Ordinal"
        cellValueFactory = _.value.ordinalProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse Ordinal"
        cellValueFactory = _.value.reverseOrdinalProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reduction"
        cellValueFactory = _.value.reductionProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse Reduction"
        cellValueFactory = _.value.reverseReductionProperty
      ,
    )
    items = Model.encodings

  children = List(tableView)
  VBox.setVgrow(tableView, Priority.Always)

final class TextPane extends HBox:
  spacing = 6
  padding = Insets(6)