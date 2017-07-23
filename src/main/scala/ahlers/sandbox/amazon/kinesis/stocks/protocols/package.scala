package ahlers.sandbox.amazon.kinesis.stocks

import ahlers.sandbox.amazon.kinesis.stocks.StockTrade.{ Id, Quantity, TradeType }
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.Writes._
import play.api.libs.json._
import squants.market._

import scala.language.implicitConversions
import scala.util.Try

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
package object protocols {

  private implicit def implyJsResult[A](v: Try[A]): JsResult[A] = v.fold({ e => JsError(e.getMessage) }, JsSuccess(_))

  implicit val MoneyReads: Reads[Money] = StringReads flatMap { v => Reads { _ => Money(v) } }
  implicit val MoneyWrites: Writes[Money] = StringWrites.contramap(_.toString)

  implicit val TickerSymbolFormat: Format[TickerSymbol] = Format(StringReads, StringWrites).inmap(TickerSymbol, _.id)

  /* Why not use Enumeratum's Play JSON support? It couples the format to the type, and buffer protocols should be kept orthogonal. */
  implicit val TradeTypeReads: Reads[TradeType] = StringReads flatMap { v => Reads { _ => Try(TradeType.withNameInsensitive(v)) } }
  implicit val TradeTypeWrites: Writes[TradeType] = StringWrites.contramap(_.entryName)

  implicit val StockTradeFormat: OFormat[StockTrade] = (
    (__ \ 'tickerSymbol).format[TickerSymbol] and
    (__ \ 'tradeType).format[TradeType] and
    (__ \ 'price).format[Money] and
    (__ \ 'quantity).format[Quantity] and
    (__ \ 'id).format[Id])(StockTrade.apply, unlift(StockTrade.unapply))

}
