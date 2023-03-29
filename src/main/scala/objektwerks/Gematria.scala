package objektwerks

import scala.collection.immutable.SortedMap

object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toList

  val ordinalCiper = SortedMap.from(
    alphabet
      .view
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) )
  )

  val reverseOrdinalCipher = SortedMap.from(
    alphabet
      .reverse
      .view
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) )
  )

  val reductionCiper = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                            'j' -> 1, 'k' -> 2, 'l' -> 3, 'm' -> 4, 'n' -> 5, 'o' -> 6, 'p' -> 7, 'q' -> 8, 'r' -> 9,
                            's' -> 1, 't' -> 2, 'u' -> 3, 'v' -> 4, 'w' -> 5, 'x' -> 6, 'y' -> 7, 'z' -> 8 )
  
  val reverseReductionCipher = Map( 'a' -> 8, 'b' -> 7, 'c' -> 6, 'd' -> 5, 'e' -> 4, 'f' -> 3, 'g' -> 2, 'h' -> 1, 'i' -> 9,
                                    'j' -> 8, 'k' -> 7, 'l' -> 6, 'm' -> 5, 'n' -> 4, 'o' -> 3, 'p' -> 2, 'q' -> 1, 'r' -> 9,
                                    's' -> 8, 't' -> 7, 'u' -> 6, 'v' -> 5, 'w' -> 4, 'x' -> 3, 'y' -> 2, 'z' -> 1 )
  
  val standardCipher = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                            'j' -> 10, 'k' -> 20, 'l' -> 30, 'm' -> 40, 'n' -> 50, 'o' -> 60, 'p' -> 70, 'q' -> 80, 'r' -> 90,
                            's' -> 100, 't' -> 200, 'u' -> 300, 'v' -> 400, 'w' -> 500, 'x' -> 600, 'y' -> 700, 'z' -> 800 )

  val reverseStandardCipher = Map( 'a' -> 800, 'b' -> 700, 'c' -> 600, 'd' -> 500, 'e' -> 400, 'f' -> 300, 'g' -> 200, 'h' -> 100, 'i' -> 90,
                                   'j' -> 80, 'k' -> 70, 'l' -> 60, 'm' -> 50, 'n' -> 40, 'o' -> 30, 'p' -> 20, 'q' -> 10, 'r' -> 9,
                                   's' -> 8, 't' -> 7, 'u' -> 6, 'v' -> 5, 'w' -> 4, 'x' -> 3, 'y' -> 2, 'z' -> 1 )

  val latinCipher = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                         'j' -> 600, 'k' -> 10, 'l' -> 20, 'm' -> 30, 'n' -> 40, 'o' -> 50, 'p' -> 60, 'q' -> 70, 'r' -> 80,
                         's' -> 90, 't' -> 100, 'u' -> 200, 'v' -> 700, 'w' -> 900, 'x' -> 300, 'y' -> 400, 'z' -> 500 )
  
  val sumerianCipher = Map( 'a' -> 6, 'b' -> 12, 'c' -> 18, 'd' -> 24, 'e' -> 30, 'f' -> 36, 'g' -> 42, 'h' -> 48, 'i' -> 54,
                            'j' -> 60, 'k' -> 66, 'l' -> 72, 'm' -> 78, 'n' -> 84, 'o' -> 90, 'p' -> 96, 'q' -> 102, 'r' -> 108,
                            's' -> 114, 't' -> 120, 'u' -> 126, 'v' -> 132, 'w' -> 138, 'x' -> 144, 'y' -> 150, 'z' -> 156 )

  val reverseSumerianCipher = Map( 'a' -> 156, 'b' -> 150, 'c' -> 144, 'd' -> 138, 'e' -> 132, 'f' -> 126, 'g' -> 120, 'h' -> 114, 'i' -> 108,
                                   'j' -> 102, 'k' -> 96, 'l' -> 90, 'm' -> 84, 'n' -> 78, 'o' -> 72, 'p' -> 66, 'q' -> 60, 'r' -> 54,
                                   's' -> 48, 't' -> 42, 'u' -> 36, 'v' -> 30, 'w' -> 24, 'x' -> 18, 'y' -> 12, 'z' -> 6 )
  /* Sumerian - 744
  a	b	 c	d	 e	f	 g	h	 i	j	 k	l	 m
  6	12 18	24 30	36 42	48 54	60 66	72 78
  n	 o	p	 q	 r	 s	 t	 u	 v	 w	 x	 y	 z
  84 90	96 102 108 114 120 126 132 138 144 150 156
  */

  /* Reverse Sumerian - 876
  z	y	 x	w	 v	u	 t	s	 r	q	 p	o	 n
  6	12 18	24 30	36 42	48 54	60 66	72 78
  m	 l	k	 j	 i	 h	 g	 f	 e	 d	 c	 b	 a
  84 90	96 102 108 114 120 126 132 138 144 150 156
  */

  def deciper(cipher: Map[Char, Int], string: String): Int =
    string
      .toCharArray
      .view
      .filter(char => char.isLetter)
      .map(letter => cipher.getOrElse(letter, 0))
      .sum