package trip.http.routes

import java.util.UUID

import cats._
import cats.data.Kleisli
import cats.implicits._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.http4s.syntax.kleisli.http4sKleisliResponseSyntaxOptionT
import trip.algebras._
import trip.domain.trip._
import trip.http.json._

final class TripRoutes[F[_]: Defer: JsonDecoder: Monad](
    trips: Trips[F]
) extends Http4sDsl[F] {

  private[routes] val prefixPath = "/api/trips"

  private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / LongVar(id) =>
      Ok(trips.findById(id))

    case req @ POST -> Root =>
      req.asJsonDecode[CreateTrip].flatMap { tripReq =>
        trips.create(tripReq) *> Created()
      }

    case DELETE -> Root / LongVar(id) =>
      Ok(trips.delete(id))
  }

  val routes: Kleisli[F, Request[F], Response[F]] = Router(
    prefixPath -> httpRoutes
  ).orNotFound
}
