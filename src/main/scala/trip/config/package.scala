package trip

import cats._
import cats.effect._
import cats.implicits._
import pureconfig._
import pureconfig.generic.auto._
import pureconfig.ConfigSource
import com.typesafe.config.ConfigFactory
import pureconfig.module.catseffect.syntax._
import trip.config.data.AppConfig

package object config {

  def load[F[_]: Sync: ContextShift]: Resource[F, AppConfig] =
    Blocker[F].flatMap { b =>
      Resource.liftF(ConfigSource.fromConfig(ConfigFactory.load()).loadF[F, AppConfig](b))
    }
}
