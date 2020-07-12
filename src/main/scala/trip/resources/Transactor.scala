package trip.resources

import doobie._
import cats.effect._
import doobie.hikari._
import trip.config._

final class Transactor[F[_]](
    transactor: Resource[F, HikariTransactor[F]]
)

object Transactor {

  def make[F[_]: Async: ContextShift](
      pgConfig: PgConfig
  ): Resource[F, HikariTransactor[F]] = {
    def mkTransactor(config: PgConfig): Resource[F, HikariTransactor[F]] =
      for {
        ce <- ExecutionContexts.fixedThreadPool[F](config.max)
        be <- Blocker[F]
        xa <- HikariTransactor.newHikariTransactor[F](
               config.driver,
               config.connection,
               config.username,
               config.password,
               ce,
               be
             )
      } yield xa

    mkTransactor(pgConfig)
  }
}
