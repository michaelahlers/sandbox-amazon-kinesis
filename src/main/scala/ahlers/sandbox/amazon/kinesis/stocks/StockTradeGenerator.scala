package ahlers.sandbox.amazon.kinesis.stocks

import squants.market._

import scala.util.Random

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object StockTradeGenerator {

  object StockPrices {

    import TickerSymbol._

    val all =
      StockPrice(AAPL, USD(119.72)) ::
        StockPrice(XOM, USD(91.56)) ::
        StockPrice(GOOG, USD(527.83)) ::
        StockPrice(`BRK.A`, USD(223999.88)) ::
        StockPrice(MSFT, USD(42.36)) ::
        StockPrice(WFC, USD(54.21)) ::
        StockPrice(JNJ, USD(99.78)) ::
        StockPrice(WMT, USD(85.91)) ::
        StockPrice(CHL, USD(66.96)) ::
        StockPrice(GE, USD(24.64)) ::
        StockPrice(NVS, USD(102.46)) ::
        StockPrice(PG, USD(85.05)) ::
        StockPrice(JPM, USD(57.82)) ::
        StockPrice(`RDS.A`, USD(66.72)) ::
        StockPrice(CVX, USD(110.43)) ::
        StockPrice(PFE, USD(33.07)) ::
        StockPrice(FB, USD(74.44)) ::
        StockPrice(VZ, USD(49.09)) ::
        StockPrice(PTR, USD(111.08)) ::
        StockPrice(BUD, USD(120.39)) ::
        StockPrice(ORCL, USD(43.40)) ::
        StockPrice(KO, USD(41.23)) ::
        StockPrice(T, USD(34.64)) ::
        StockPrice(DIS, USD(101.73)) ::
        StockPrice(AMZN, USD(370.56)) ::
        Nil

    def random: StockPrice = all(Random.nextInt(all.size))

  }

  val MaxDeviation = 0.2

  val MaxQuantity = 10000

  val ProbabilitySell = 0.4

  def random: StockTrade = {
    import StockTrade._
    import TradeType._

    val stockPrice = StockPrices.random
    import stockPrice._

    val deviation = (Random.nextDouble() - 0.5) * 2.0 * MaxDeviation
    val price = stockPrice.price * (1 + deviation)
    val tradeType = if (Random.nextDouble() < ProbabilitySell) Sell else Buy
    val quantity = Random.nextInt(MaxQuantity) + 1

    StockTrade(tickerSymbol, tradeType, price, quantity)
  }

}
