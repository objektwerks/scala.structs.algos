package objektwerks.gui

import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TableColumn, TableView, TextField}
import scalafx.scene.image.Image
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{BorderPane, GridPane, HBox, Priority, VBox}

import objektwerks.Ciphers.*
import objektwerks.Numbers.{isPrime, isStar, isTriangular, findPrimeRank, listFactors, listPrimes}

object CipherApp extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View.scene
      title = "Ciphers"
      minWidth = View.width
      minHeight = View.height
      icons.add( new Image( Image.getClass.getResourceAsStream("/cipher.png") ) )
    stage.show()

object Model:
  val observableEncodings = ObservableBuffer[Encodings]()
  val observableEncoding = ObjectProperty[Int](0)
  val primes = listPrimes(0 to 1000)

  def primeRank(prime: Int): Int = findPrimeRank(primes, prime)

  def encode(text: String): Unit = observableEncodings += Encodings.encode(text)

  def clear(): Unit =
    observableEncodings.clear()
    observableEncoding.value = 0

object View:
  val width = 800
  val height = 400

  val borderPane = new BorderPane:
    prefWidth = View.width
    prefHeight = View.height
    padding = Insets(6)

  borderPane.top = NumberPane()
  borderPane.center = EncodingsPane()
  borderPane.bottom = TextNumberPane()

  val scene = new Scene:
    root = borderPane
    stylesheets = List("/style.css")

final class NumberPane extends HBox:
  spacing = 6
  padding = Insets(6)

  val label = new Label:
    alignment = Pos.CenterLeft
    text = "Number:"

  val number = new Label:
    alignment = Pos.CenterLeft
    text = ""

  Model.observableEncoding.onChange { (_, _, newValue) =>
    val prime = if isPrime(newValue) then s"Yes - #${Model.primeRank(newValue)}" else "No"
    val star = if isStar(newValue) then "Yes" else "No"
    val triangular = if isTriangular(newValue) then "Yes" else "No"
    val factors = listFactors(newValue).mkString(", ")
    number.text = s"$newValue      Prime: $prime      Star: $star      Triangular: $triangular      Factors: $factors"
  }

  val grid = new GridPane:
    hgap = 6
    vgap = 6
    padding = Insets(top = 6, right = 6, bottom = 6, left = 6)
    add(label, columnIndex = 0, rowIndex = 0)
    add(number, columnIndex = 1, rowIndex = 0)

  children = List(grid)
  HBox.setHgrow(grid, Priority.Always)

final class EncodingsPane extends VBox:
  spacing = 6
  padding = Insets(6)

  val tableView = new TableView[Encodings]():
    columns ++= List(
      new TableColumn[Encodings, String]:
        text = "Text"
        prefWidth = 200
        cellValueFactory = _.value.textProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Ordinal"
        cellValueFactory = _.value.ordinalProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nOrdinal"
        cellValueFactory = _.value.reverseOrdinalProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reduction"
        cellValueFactory = _.value.reductionProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nReduction"
        cellValueFactory = _.value.reverseReductionProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Latin"
        cellValueFactory = _.value.latinProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Chaldean"
        cellValueFactory = _.value.chaldeanProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Septenary"
        cellValueFactory = _.value.septenaryProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Keypad"
        cellValueFactory = _.value.keypadProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Standard"
        cellValueFactory = _.value.standardProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nStandard"
        cellValueFactory = _.value.reverseStandardProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Sumerian"
        cellValueFactory = _.value.sumerianProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nSumerian"
        cellValueFactory = _.value.reverseSumerianProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Primes"
        cellValueFactory = _.value.primesProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nPrimes"
        cellValueFactory = _.value.reversePrimesProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Fibonacci"
        cellValueFactory = _.value.fibonacciProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Squares"
        cellValueFactory = _.value.squaresProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nSquares"
        cellValueFactory = _.value.reverseSquaresProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Trigonal"
        cellValueFactory = _.value.trigonalProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nTrigonal"
        cellValueFactory = _.value.reverseTrigonalProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Single\nReduction"
        cellValueFactory = _.value.singleReductionProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nSingle\nReduction"
        cellValueFactory = _.value.reverseSingleReductionProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Satanic"
        cellValueFactory = _.value.satanicProperty
      ,
      new TableColumn[Encodings, Int]:
        text = "Reverse\nSatanic"
        cellValueFactory = _.value.reverseSatanicProperty
    )
    items = Model.observableEncodings

  val tableViewSelectionModel = tableView.selectionModel.apply()
  tableViewSelectionModel.setCellSelectionEnabled(true)
  val selectedCells = tableViewSelectionModel.getSelectedCells()
  selectedCells.onChange {
    if selectedCells.nonEmpty then 
      val tablePosition = selectedCells.get(0)
      val cellRow = tablePosition.getRow()
      Model.observableEncoding.value = tablePosition.getTableColumn().getCellData(cellRow).asInstanceOf[Int]
  }

  children = List(tableView)
  VBox.setVgrow(tableView, Priority.Always)

final class TextNumberPane extends HBox:
  spacing = 6
  padding = Insets(6)

  val textLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Text:"

  val textField = new TextField:
    hgrow = Priority.Always
    onKeyReleased = (event: KeyEvent) => if event.code == KeyCode.Enter then Model.encode(text.value)

  val numberLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Number:"

  val numberField = new TextField:
    hgrow = Priority.Always
    onKeyReleased = (event: KeyEvent) => if event.code == KeyCode.Enter then Model.observableEncoding.value = text.value.toIntOption.getOrElse(0)

  val clearButton = new Button:
    text = "Clear"
    onAction = { _ =>
      textField.text = ""
      numberField.text = ""
      Model.clear()
    }

  val grid = new GridPane:
    hgap = 6
    vgap = 6
    padding = Insets(top = 6, right = 6, bottom = 6, left = 6)
    add(textLabel, columnIndex = 0, rowIndex = 0)
    add(textField, columnIndex = 1, rowIndex = 0)
    add(numberLabel, columnIndex = 2, rowIndex = 0)
    add(numberField, columnIndex = 3, rowIndex = 0)
    add(clearButton, columnIndex = 4, rowIndex = 0)

  children = List(grid)
  HBox.setHgrow(grid, Priority.Always)