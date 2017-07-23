package ahlers.sandbox.amazon.kinesis.stocks

import java.util.concurrent.atomic.AtomicLong

import ahlers.sandbox.amazon.kinesis.stocks.StockTrade._
import enumeratum._
import squants.market._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
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
