package trip.config

import cats.effect._
import pureconfig.ConfigSource
import pureconfig.module.catseffect.syntax._
import pureconfig.generic.auto._
import com.typesafe.config.ConfigFactory

object Config {

  def load[F[_]: Sync: ContextShift]: Resource[F, AppConfig] =
    Blocker[F].flatMap { b =>
      Resource.liftF(ConfigSource.fromConfig(ConfigFactory.load()).loadF[F, AppConfig](b))
    }
}
