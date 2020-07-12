package trip

import cats.effect._
import org.http4s.server.blaze.BlazeServerBuilder
import trip.algebras._
import trip.config._
import trip.http.routes._
import trip.resources._

object Application extends IOApp {

  override def run(args: List[String]) =
    Config.load[IO].use { config =>
      val xa = Transactor.make[IO](config.pgConfig)
      for {
        trips <- LiveTrips.make[IO](xa)
        routes = new TripRoutes[IO](trips)
        _ <- BlazeServerBuilder[IO]
              .bindHttp(5555, "localhost")
              .withHttpApp(routes.routes)
              .serve
              .compile
              .drain
      } yield ExitCode.Success
    }
}
