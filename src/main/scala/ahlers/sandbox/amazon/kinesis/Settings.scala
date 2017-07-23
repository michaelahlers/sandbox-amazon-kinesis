package ahlers.sandbox.amazon.kinesis

import com.amazonaws.regions.{ Region, RegionUtils }
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._
import net.ceedubs.ficus.readers.ValueReader

import scala.concurrent.duration.FiniteDuration

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class Settings(amazon: Settings.Amazon, sandbox: Settings.Sandbox)
object Settings {

  case class Amazon(kinesis: Amazon.Kinesis)
  object Amazon {

    case class Credential(profile: Option[Credential.Profile])
    object Credential {
      type Profile = String
    }

    case class Kinesis(region: Region, credential: Credential)

  }

  case class Sandbox(amazon: Sandbox.Amazon)
  object Sandbox {

    case class Amazon(kinesis: Amazon.Kinesis)
    object Amazon {

      case class Kinesis(stocks: Kinesis.Stocks)
      object Kinesis {

        case class Stocks(producers: Stocks.Producers, trades: Stocks.Trades, delay: FiniteDuration)
        object Stocks {
          type Producers = Int
          type Trades = Int
        }

      }

    }

  }

  /* Uses a work-around for iheartradio/ficus#8. */
  lazy val instance: Settings = {
    implicit val RegionReader: ValueReader[Region] = stringValueReader map RegionUtils.getRegion
    ConfigFactory.load()
      .atKey("x")
      .as[Settings]("x")
  }

}

