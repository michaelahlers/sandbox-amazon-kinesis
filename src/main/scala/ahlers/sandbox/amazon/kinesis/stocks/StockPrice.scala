package ahlers.sandbox.amazon.kinesis.stocks

import squants.market._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class StockPrice(tickerSymbol: TickerSymbol, price: Money)
