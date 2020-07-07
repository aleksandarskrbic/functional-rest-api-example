package trip

import cats.effect.{ ExitCode, IO, IOApp }
import org.http4s.HttpApp
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.{ RequestLogger, ResponseLogger }
import trip.algebras.{ LiveTrips, TripQueries, Trips }
import trip.http.routes.TripRoutes
import trip.resources.Transactor

object Application extends IOApp {

  override def run(args: List[String]) =
    config.load[IO].use { config =>
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
