package trip

import scala.concurrent.duration.FiniteDuration

package object config {

  final case class AppConfig(
      pgConfig: PgConfig
  )

  final case class PgConfig(
      host: String,
      port: Int,
      database: String,
      driver: String,
      username: String,
      password: String,
      max: Int
  ) {
    def connection = s"jdbc:postgresql://$host:$port/$database"
  }

  final case class HttpClientConfig(
      connectTimeout: FiniteDuration,
      requestTimeout: FiniteDuration
  )
}
