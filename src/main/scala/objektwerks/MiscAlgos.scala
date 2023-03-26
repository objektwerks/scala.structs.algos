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
    val maxSellPricesFromIonward = stockPrices
      .scanRight(0) {
        case (maxPriceSoFar, dayPrice) => Math.max(maxPriceSoFar, dayPrice)
      }
      .toArray
    val maxSellPricesAfterI = maxSellPricesFromIonward.drop(1)

    if stockPrices.length < 2 then None
    else
      stockPrices
        .zip(maxSellPricesAfterI)
        .map {
          case (buyPrice, sellPrice) => getPotentialProfit(buyPrice, sellPrice)
        }
        .max

  private def getPotentialProfit(buyPrice: Int, sellPrice: Int): Option[Int] =
    if sellPrice > buyPrice then Some(sellPrice - buyPrice)
    else None