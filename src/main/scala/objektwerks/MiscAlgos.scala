package objektwerks

object MiscAlgos:
  def findUnpairedItem[T](list: List[T]): Option[T] =
    list
      .foldLeft[Set[T]](Set.empty) {
        case (set, t) if set.contains(t) => set - t
        case (set, t) => set + t
      }
      .headOption

  def findMaxProfit(stockPrices: List[Int]): Option[Int] =
    val maxSellPrices = stockPrices
      .scanRight(0) {
        case (maxPrice, dayPrice) => Math.max(maxPrice, dayPrice)
      }
      .drop(1)

    if stockPrices.length < 2 then None
    else
      stockPrices
        .zip(maxSellPrices)
        .map {
          case (buyPrice, sellPrice) => getPotentialProfit(buyPrice, sellPrice)
        }
        .max

  private def getPotentialProfit(buyPrice: Int, sellPrice: Int): Option[Int] =
    if sellPrice > buyPrice then Some(sellPrice - buyPrice)
    else None