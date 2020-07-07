package trip.http

import cats.Applicative
import io.circe._
import io.circe.generic.semiauto._
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf
import trip.domain.trip._

object json extends JsonCodecs {
  implicit def deriveEntityEncoder[F[_]: Applicative, A: Encoder]: EntityEncoder[F, A] = jsonEncoderOf[F, A]
}

private[http] trait JsonCodecs {

  implicit val tripDecoder: Decoder[Trip] = deriveDecoder[Trip]
  implicit val tripEncoder: Encoder[Trip] = deriveEncoder[Trip]

  implicit val createTripDecoder: Decoder[CreateTrip] = deriveDecoder[CreateTrip]
}
