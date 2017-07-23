package ahlers.sandbox.amazon.kinesis.stocks

import scala.util.Random

/**
 * @see [[https://github.com/awslabs/amazon-kinesis-learning/blob/181ee5830b4401b54e64c9a784ea2973d96e381a/src/com/amazonaws/services/kinesis/samples/stocktrades/writer/StockTradeGenerator.java StockTradeGenerator]]
 */
object StockTrades {

  val MaxDeviation = 0.2

  val MaxQuantity = 10000

  val ProbabilitySell = 0.4

  val random: Stream[StockTrade] =
    StockPrices.random map { stockPrice =>
      import StockTrade._
      import TradeType._
      import stockPrice._

      val deviation = (Random.nextDouble() - 0.5) * 2.0 * MaxDeviation
      val price = stockPrice.price * (1 + deviation)
      val tradeType = if (Random.nextDouble() < ProbabilitySell) Sell else Buy
      val quantity = Random.nextInt(MaxQuantity) + 1

      StockTrade(tickerSymbol, tradeType, price, quantity)
    }

}
