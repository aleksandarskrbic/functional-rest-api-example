package trip.domain

import java.time.LocalDate
import java.util.UUID

object trip {

  final case class Trip(
      id: Long,
      destination: String,
      startDate: LocalDate,
      endDate: LocalDate,
      description: Option[String]
  )

  final case class CreateTrip(
      destination: String,
      startDate: LocalDate,
      endDate: LocalDate,
      description: Option[String]
  )
}
