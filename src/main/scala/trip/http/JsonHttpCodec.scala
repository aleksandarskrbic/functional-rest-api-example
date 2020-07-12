package trip.http

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import cats.Applicative
import io.circe._
import io.circe.generic.semiauto._
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf
import trip.domain.trip._
import io.circe.Decoder

object JsonHttpCodec extends JsonCodecs {
  implicit def deriveEntityEncoder[F[_]: Applicative, A: Encoder]: EntityEncoder[F, A] = jsonEncoderOf[F, A]
}

private[http] trait JsonCodecs {

  implicit val tripDecoder: Decoder[Trip] = deriveDecoder[Trip]
  implicit val tripEncoder: Encoder[Trip] = deriveEncoder[Trip]

  implicit val createTripDecoder: Decoder[CreateTrip] = deriveDecoder[CreateTrip]

  implicit val decodeLocalDate: Decoder[LocalDate] =
    Decoder.decodeLocalDateWithFormatter(DateTimeFormatter.ofPattern("dd/MM/uuuu"))
}
