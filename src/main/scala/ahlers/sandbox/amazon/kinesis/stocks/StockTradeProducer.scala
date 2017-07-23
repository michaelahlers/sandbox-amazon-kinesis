package ahlers.sandbox.amazon.kinesis.stocks

import ahlers.sandbox.amazon.kinesis._
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder
import com.amazonaws.services.kinesis.model.PutRecordRequest
import com.typesafe.scalalogging.LazyLogging

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object StockTradeProducer extends App with LazyLogging {

  import Settings.{ instance => settings }
  import settings.sandbox.amazon.kinesis.stocks._

  val client =
    AmazonKinesisClientBuilder
      .standard()
      .withSettings(settings)
      .build()

  StockTrades.random.take(trades) foreach { stockTrade =>
    import stockTrade._

    val request = {
      import java.nio.ByteBuffer._
      import protocols._
      import play.api.libs.json.Json._

      val r = new PutRecordRequest()
      r.setStreamName("sandbox-amazon-kinesis")
      r.setPartitionKey(tickerSymbol.id)
      r.setData(wrap(toBytes(toJson(stockTrade))))
      r
    }

    logger.debug("Producing stock trade {}.", stockTrade)

    client.putRecord(request)
    Thread.sleep(delay.toMillis)
  }

}
