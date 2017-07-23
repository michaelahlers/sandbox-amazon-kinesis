package ahlers.sandbox.amazon.kinesis.stocks

import ahlers.sandbox.amazon.kinesis.stocks.StockTrade.TradeType
import enumeratum.{ Enum, EnumEntry }

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
sealed trait TickerSymbol extends EnumEntry
object TickerSymbol extends Enum[TradeType] {
  val values = findValues
  case object AAPL extends TickerSymbol
  case object AMZN extends TickerSymbol
  case object `BRK.A` extends TickerSymbol
  case object BUD extends TickerSymbol
  case object CHL extends TickerSymbol
  case object CVX extends TickerSymbol
  case object DIS extends TickerSymbol
  case object FB extends TickerSymbol
  case object GE extends TickerSymbol
  case object GOOG extends TickerSymbol
  case object JNJ extends TickerSymbol
  case object JPM extends TickerSymbol
  case object KO extends TickerSymbol
  case object MSFT extends TickerSymbol
  case object NVS extends TickerSymbol
  case object ORCL extends TickerSymbol
  case object PFE extends TickerSymbol
  case object PG extends TickerSymbol
  case object PTR extends TickerSymbol
  case object `RDS.A` extends TickerSymbol
  case object T extends TickerSymbol
  case object VZ extends TickerSymbol
  case object WFC extends TickerSymbol
  case object WMT extends TickerSymbol
  case object XOM extends TickerSymbol
}
