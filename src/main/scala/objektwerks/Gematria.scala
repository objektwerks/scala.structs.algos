package objektwerks

import scala.collection.immutable.SortedMap

object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toList
  val ordinalMap = SortedMap.from(
    alphabet
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) )
  )
  val reverseOrdinalMap = SortedMap.from(
    alphabet
      .reverse
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) ) 
  )
  val reductionMap = Map( 'a' -> 1,'b' -> 2,'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                          'j' -> 1,'k' -> 2,'l' -> 3, 'm' -> 4, 'n' -> 5, 'o' -> 6, 'p' -> 7, 'q' -> 8, 'r' -> 9,
                          's' -> 1,'t' -> 2,'u' -> 3, 'v' -> 4, 'w' -> 5, 'x' -> 6, 'y' -> 7, 'z' -> 8
                         )

  /* Reduction
  a	b	c	d	e	f	g	h	i	j	k	l	m	n	o	p	q	r	s	t	u	v	w	x	y	z
  1	2	3	4	5	6	7	8	9	1	2	3	4	5	6	7	8	9	1	2	3	4	5	6	7	8
  */

  /* Reverse Reduction
  z	y	x	w	v	u	t	s	r	q	p	o	n	m	l	k	j	i	h	g	f	e	d	c	b	a
  1	2	3	4	5	6	7	8	9	1	2	3	4	5	6	7	8	9	1	2	3	4	5	6	7	8
  */

  def deciper(cipher: SortedMap[Char, Int], string: String): Int =
    string
      .toCharArray
      .filter(char => char.isLetter)
      .map(letter => cipher.getOrElse(letter, 0))
      .sum