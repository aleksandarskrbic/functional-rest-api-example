/*
package trip

import cats.effect._
import doobie._
import doobie.hikari._
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import trip.config.data.{ HttpClientConfig, PgConfig }

import scala.concurrent.ExecutionContext

final case class AppResources[F[_]](
    pg: Resource[F, HikariTransactor[F]],
    client: Resource[F, Client[F]]
)

object AppResources {

  def make[F[_]: ConcurrentEffect: ContextShift](
      pgConfig: PgConfig,
      httpConfig: HttpClientConfig
  ): Resource[F, AppResources[F]] = {

    def mkTransactor(config: PgConfig): Resource[F, HikariTransactor[F]] =
      for {
        ce <- ExecutionContexts.fixedThreadPool[F](4) // our connect EC
        be <- Blocker[F] // our blocking EC
        xa <- HikariTransactor.newHikariTransactor[F](
               "org.postgresql.Driver", // driver classname
               "jdbc:postgresql:world", // connect URL
               "", // username
               "", // password
               ce, // await connection here
               be // execute JDBC operations here
             )
      } yield xa

    def mkHttpClient(c: HttpClientConfig): Resource[F, Client[F]] =
      BlazeClientBuilder[F](ExecutionContext.global)
        .withConnectTimeout(c.connectTimeout)
        .withRequestTimeout(c.requestTimeout)
        .resource

    Resource.liftF {
      Async[F].delay(
        AppResources(mkTransactor(pgConfig), mkHttpClient(httpConfig))
      )
    }
  }
}
*/
