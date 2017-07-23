package ahlers.sandbox.amazon.kinesis.stocks

import squants.market.Money

import scala.util.Random

/**
 * @see [[https://github.com/awslabs/amazon-kinesis-learning/blob/181ee5830b4401b54e64c9a784ea2973d96e381a/src/com/amazonaws/services/kinesis/samples/stocktrades/writer/StockTradeGenerator.java StockTradeGenerator]]
 */
object StockPrices {

  val all: List[StockPrice] = {
    import kantan.csv._
    import kantan.csv.ops.source._

    implicit val TickerSymbolDecoder = CellDecoder from { v => DecodeResult(TickerSymbol(v)) }
    implicit val MoneyDecoder = CellDecoder from { v => DecodeResult.fromTry(Money(v)) }
    implicit val StockPriceDecoder = HeaderDecoder.decoder("Symbol", "Price")(StockPrice)

    StockPrices.getClass
      .getResource("/ahlers/sandbox/amazon/kinesis/stocks/stock-trade-generator_stock-prices.csv")
      .asCsvReader[StockPrice](rfc)
      .toList
      .collect({ case Success(stockPrice) => stockPrice })
  }

  def random: StockPrice = all(Random.nextInt(all.size))

}
