package objektwerks

object JustifyTextApp {
  import scala.annotation.tailrec

  def main(args: Array[String]): Unit = {
    val text ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam vel urna bibendum, pharetra mi quis, imperdiet nibh. Praesent dictum odio lacus, eget commodo sem aliquam rutrum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec eget rhoncus mauris, quis vehicula mi. Quisque finibus purus non varius dictum. Pellentesque vulputate fringilla egestas. Nunc eleifend ex sed egestas cursus. Praesent molestie nisl in pretium vehicula. Vestibulum efficitur ut risus quis porta. Praesent non sem quam. Donec vitae arcu sapien. Quisque aliquet nibh in metus efficitur ullamcorper. Donec mattis dapibus nisl sed iaculis. Curabitur eu blandit enim. Fusce varius."
    val justifiedText = justify(text, 40)
    println(justifiedText)
  }

  def justify(text: String, width: Int): String = {
    def createSpaces(n: Int): String = (1 to n).map(_ => " ").mkString("")

    @tailrec
    def pack(words: List[String], currentRow: List[String], currentCharCount: Int, result: List[List[String]]): List[List[String]] = {
      if (words.isEmpty && currentRow.isEmpty) {
        // nothing else to add
        result
      } else if (words.isEmpty) {
        // add the last row
        result :+ currentRow
      } else if (currentRow.isEmpty && words.head.length > width) {
        // split the word into supercalifra-gilistic
        val (partOnThisRow, partOnNextRow) = words.head.splitAt(width - 2) // at width - 1 put a '-'
        pack(partOnNextRow :: words.tail, List(), 0, result :+ List(partOnThisRow + "-"))
      } else if (words.head.length + currentCharCount > width) {
        // fetch a new row
        pack(words, List(), 0, result :+ currentRow)
      } else {
        // put the word into the current row
        pack(words.tail, currentRow :+ words.head, currentCharCount + 1 + words.head.length, result)
      }
    }

    def justifyRow(row: List[String]): String = {
      if (row.length == 1) row.head
      else {
        val nSpacesAvailable = width - row.map(_.length).sum
        val nIntervals = row.length - 1
        val nSpacesPerInterval = nSpacesAvailable / nIntervals
        val nExtraSpaces = nSpacesAvailable % nIntervals
        val regularSpace = createSpaces(nSpacesPerInterval)
        val biggerSpace = createSpaces(nSpacesPerInterval + 1)
        if (nExtraSpaces == 0) row.mkString(regularSpace)
        else {
          val nWordsWithBiggerIntervals = nExtraSpaces + 1
          val wordsWithBiggerIntervals = row.take(nWordsWithBiggerIntervals)
          val firstPart = wordsWithBiggerIntervals.mkString(biggerSpace)
          val secondPart = row.drop(nWordsWithBiggerIntervals).mkString(regularSpace)
          firstPart + regularSpace + secondPart
        }
      }
    }

    assert(width > 2) // split text into words
    val words = text.split(" ").toList // pack the words into rows
    val unjustifiedRows = pack(words, List(), 0, List()) // justify the rows
    val justifiedRows = unjustifiedRows.init.map(justifyRow) :+ unjustifiedRows.last.mkString(" ") // rebuild the justified text
    justifiedRows.mkString("\n")
  }
}