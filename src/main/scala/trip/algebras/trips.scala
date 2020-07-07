package trip.algebras

import doobie._
import doobie.implicits._
import doobie.util.ExecutionContexts
import doobie.hikari.HikariTransactor
import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import trip.domain.trip._
import doobie.implicits.legacy.localdate._

trait Trips[F[_]] {
  def create(trip: CreateTrip): F[Unit]
  def findById(id: Long): F[Option[Trip]]
  def delete(id: Long): F[Unit]
}

object LiveTrips {
  def make[F[_]: Sync](
      transactor: Resource[F, HikariTransactor[F]]
  ): F[Trips[F]] =
    Sync[F].delay(
      new LiveTrips[F](transactor)
    )
}

final class LiveTrips[F[_]: Sync] private (
    transactor: Resource[F, HikariTransactor[F]]
) extends Trips[F] {

  override def create(trip: CreateTrip): F[Unit] =
    transactor.use { xa =>
      TripQueries.insert(trip).run.transact(xa).map(_ => ())
    }

  override def findById(id: Long): F[Option[Trip]] =
    transactor.use { xa =>
      TripQueries.findById(id).option.transact(xa)
    }

  override def delete(id: Long): F[Unit] =
    transactor.use { xa =>
      TripQueries.delete(id).run.transact(xa).map(_ => ())
    }
}

private object TripQueries {

  def insert(trip: CreateTrip) =
    sql"""
         | INSERT INTO trips (destination, start_date, end_date, description)
         | VALUES (${trip.description}, ${trip.startDate}, ${trip.endDate}, ${trip.description})
         |""".stripMargin.update

  def findById(id: Long) =
    sql"SELECT * FROM trips WHERE id = $id".query[Trip]

  def delete(id: Long) =
    sql"DELETE FROM trips WHERE id = $id".update
}
