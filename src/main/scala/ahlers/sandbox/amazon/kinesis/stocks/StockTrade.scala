package ahlers.sandbox.amazon.kinesis.stocks

import java.util.concurrent.atomic.AtomicLong

import ahlers.sandbox.amazon.kinesis.stocks.StockTrade._
import enumeratum._
import squants.market._

/**
 * @see [[https://github.com/awslabs/amazon-kinesis-learning/blob/181ee5830b4401b54e64c9a784ea2973d96e381a/src/com/amazonaws/services/kinesis/samples/stocktrades/model/StockTrade.java StockTrade]]
 */
case class StockTrade(tickerSymbol: TickerSymbol, tradeType: TradeType, price: Money, quantity: Quantity, id: Id = nextId())

object StockTrade {

  val nextId: () => Long = {
    val seed = new AtomicLong()
    seed.getAndIncrement _
  }

  type Quantity = Long
  type Id = Long

  sealed trait TradeType extends EnumEntry
  object TradeType extends Enum[TradeType] {
    val values = findValues
    case object Buy extends TradeType
    case object Sell extends TradeType
  }

}
