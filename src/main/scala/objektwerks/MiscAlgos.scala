package objektwerks

object MiscAlgos:
  def findUnpairedItem[T](list: List[T]): Option[T] =
    list
      .foldLeft[Set[T]](Set.empty) {
        case (set, t) if set.contains(t) => set - t
        case (set, t) => set + t
      }
      .headOption

  def findMaxProfit(prices: List[Int]): Option[Int] =
    val maxSellPrices = prices
      .view
      .scanRight(0) {
        (maxPrice, dayPrice) => Math.max(maxPrice, dayPrice)
      }
      .drop(1)

    if prices.length < 2 then None
    else
      prices
        .view
        .zip(maxSellPrices)
        .map {
          (buyPrice, sellPrice) => if sellPrice > buyPrice then Some(sellPrice - buyPrice) else None
        }
        .max