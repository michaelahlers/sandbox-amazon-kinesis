package ahlers.sandbox.amazon.kinesis.stocks

import squants.market._

/**
 * @see [[https://github.com/awslabs/amazon-kinesis-learning/blob/181ee5830b4401b54e64c9a784ea2973d96e381a/src/com/amazonaws/services/kinesis/samples/stocktrades/writer/StockTradeGenerator.java StockTradeGenerator]]
 */
case class StockPrice(tickerSymbol: TickerSymbol, price: Money)
