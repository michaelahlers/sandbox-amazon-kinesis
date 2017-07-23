package ahlers.sandbox.amazon

import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder

import scala.language.implicitConversions

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
package object kinesis {

  implicit def implyBuilderOps(v: AmazonKinesisClientBuilder): AmazonKinesisClientBuilderOps = AmazonKinesisClientBuilderOps(v)

}
