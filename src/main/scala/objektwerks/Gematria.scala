package objektwerks

import scala.collection.immutable.SortedMap

object Gematria:
  val piCiper = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 6, 'i' -> 5,
                     'j' -> 4, 'k' -> 3, 'l' -> 2, 'm' -> 1, 'n' -> 1, 'o' -> 2, 'p' -> 3, 'q' -> 4, 'r' -> 5,
                     's' -> 6, 't' -> 7, 'u' -> 6, 'v' -> 5, 'w' -> 4, 'x' -> 3, 'y' -> 2, 'z' -> 1 )

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

  val primesCiper = Map( 'a' -> 2, 'b' -> 3, 'c' -> 5, 'd' -> 7, 'e' -> 11, 'f' -> 13, 'g' -> 17, 'h' -> 19, 'i' -> 23,
                         'j' -> 29, 'k' -> 31, 'l' -> 37, 'm' -> 41, 'n' -> 43, 'o' -> 47, 'p' -> 53, 'q' -> 59, 'r' -> 61,
                         's' -> 67, 't' -> 71, 'u' -> 73, 'v' -> 79, 'w' -> 83, 'x' -> 89, 'y' -> 97, 'z' -> 101 )

  val reversePrimesCiper = Map( 'a' -> 101, 'b' -> 97, 'c' -> 89, 'd' -> 83, 'e' -> 79, 'f' -> 73, 'g' -> 71, 'h' -> 67, 'i' -> 61,
                                'j' -> 59, 'k' -> 53, 'l' -> 47, 'm' -> 43, 'n' -> 41, 'o' -> 37, 'p' -> 31, 'q' -> 29, 'r' -> 23,
                                's' -> 19, 't' -> 17, 'u' -> 13, 'v' -> 11, 'w' -> 7, 'x' -> 5, 'y' -> 3, 'z' -> 2 )

  val fibonacciCiper = Map( 'a' -> 1, 'b' -> 1, 'c' -> 2, 'd' -> 3, 'e' -> 5, 'f' -> 8, 'g' -> 13, 'h' -> 21, 'i' -> 34,
                            'j' -> 55, 'k' -> 89, 'l' -> 144, 'm' -> 233, 'n' -> 233, 'o' -> 144, 'p' -> 89, 'q' -> 55, 'r' -> 34,
                            's' -> 21, 't' -> 13, 'u' -> 8, 'v' -> 5, 'w' -> 3, 'x' -> 2, 'y' -> 1, 'z' -> 1 )

  /* Squares - 1840
  a	b	c	d	 e	f	 g	h	 i	j	  k	  l	  m
  1	4	9	16 25	36 49	64 81	100	121	144	169
  n	  o	  p	  q	  r	  s	  t	  u	  v	  w	  x	  y	  z
  196	225	256	289	324	361	400	441	484	529	576	625	676
  */

  /* Reverse Squares - 2434
    z	y	x	w	 v	u	 t	s	 r	q	  p	  o	  n
    1	4	9	16 25	36 49	64 81	100	121	144	169
    m	  l	  k	  j	  i	  h	  g	  f	  e	  d	  c	  b	  a
    196	225	256	289	324	361	400	441	484	529	576	625	676
  */

  /* Trigonal - 982
    a	b	c	d	 e	f	 g	h	 i	j	 k	l	 m
    1	3	6	10 15	21 28	36 45	55 66	78 91
    n	  o	  p	  q	  r	  s	  t	  u	  v	  w	  x	  y	  z
    105	120	136	153	171	190	210	231	253	276	300	325	351
  */

  /* Reverse Trigonal - 1290
    z	y	x	w	 v	u	 t	s	 r	q	 p	o	 n
    1	3	6	10 15	21 28	36 45	55 66	78 91
    m	  l	  k	  j	  i	  h	  g	  f	  e	  d	  c	  b	  a
    105	120	136	153	171	190	210	231	253	276	300	325	351
  */

  def encipher(cipher: Map[Char, Int], text: String): Int =
    text
      .toCharArray
      .view
      .filter(char => char.isLetter)
      .map(letter => cipher.getOrElse(letter, 0))
      .sum

  def encipherToMap(cipher: Map[Char, Int], text: String): List[(Char, Int)] =
    text
      .toCharArray
      .view
      .filter(char => char.isLetter)
      .map(letter => letter -> cipher.getOrElse(letter, 0))
      .toList