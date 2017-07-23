package ahlers.sandbox.amazon.kinesis

import com.amazonaws.auth.{ AWSCredentialsProvider, AWSCredentialsProviderChain }
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder

import scala.collection.convert.ImplicitConversionsToJava._

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
case class AmazonKinesisClientBuilderOps(builder: AmazonKinesisClientBuilder) extends AnyVal {

  def withSettings(settings: Settings): AmazonKinesisClientBuilder = {
    import settings.amazon.kinesis.region

    val providers: List[Option[AWSCredentialsProvider]] = {
      import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
      import com.amazonaws.auth.profile.ProfileCredentialsProvider
      import settings.amazon.kinesis.credential.profile

      Some(new EnvironmentVariableCredentialsProvider) ::
        profile.map(new ProfileCredentialsProvider(_)) ::
        Nil
    }

    builder
      .withRegion(region.getName)
      .withCredentials(new AWSCredentialsProviderChain(providers.flatten))
  }

}
