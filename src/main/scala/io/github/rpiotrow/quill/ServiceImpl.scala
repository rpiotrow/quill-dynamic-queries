package io.github.rpiotrow.quill

import cats.data.Kleisli
import cats.effect.{Blocker, ContextShift, IO, Resource}
import doobie.h2.H2Transactor
import doobie.implicits._
import doobie.util.transactor.Transactor
import io.github.rpiotrow.quill.domain.Organization

import java.sql.Connection
import scala.concurrent.ExecutionContext;

class ServiceImpl(repository: Repository, tnx: Transactor[IO]) extends Service[IO] {

  override def getOrganizationsForThingsDynamic: IO[Seq[Organization]] =
    repository.getOrganizationsForThingsDynamic(Seq("thing1", "thing2"))
      .transact(tnx)

  override def getOrganizationsForThingsStatic: IO[Seq[Organization]] =
    repository.getOrganizationsForThingsStatic(Seq("thing1", "thing2"))
      .transact(tnx)

}

object ServiceImpl {

  def create: Resource[IO, Service[IO]] = {
    for {
      blocker <- Blocker[IO]
      tnx     <- makeTnx(ExecutionContext.global, blocker)
      _       <- Resource.liftF(tnx.exec.apply(prepareDb))
    } yield new ServiceImpl(new Repository, tnx)

  }

  private def makeTnx(ec: ExecutionContext, blocker: Blocker): Resource[IO, Transactor[IO]] = {
    implicit val cs: ContextShift[IO] = IO.contextShift(ec)
    H2Transactor.newH2Transactor[IO](
      url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
      user = "sa",
      pass = "",
      connectEC = ec,
      blocker = blocker
    )
  }

  private val prepareDb: Kleisli[IO, Connection, Unit] = Kleisli(connection => IO {
    val statement = connection.createStatement
    val sql: String =
      """
        |create table organization(id VARCHAR(100) PRIMARY KEY, name VARCHAR(100), data1 VARCHAR(100), data2 VARCHAR(100), data3 VARCHAR(100), data4 VARCHAR(100), data5 VARCHAR(100), data6 VARCHAR(100), data7 VARCHAR(100), data8 VARCHAR(100), data9 VARCHAR(100), data10 VARCHAR(100), data11 VARCHAR(100), data12 VARCHAR(100), data13 VARCHAR(100), data14 VARCHAR(100), data15 VARCHAR(100), data16 VARCHAR(100));
        |create table person(id VARCHAR(100) PRIMARY KEY, organization_id VARCHAR(100), name VARCHAR(100), data1 VARCHAR(100), data2 VARCHAR(100), data3 VARCHAR(100), data4 VARCHAR(100), data5 VARCHAR(100), data6 VARCHAR(100), data7 VARCHAR(100), data8 VARCHAR(100), data9 VARCHAR(100), data10 VARCHAR(100), data11 VARCHAR(100), data12 VARCHAR(100), data13 VARCHAR(100), data14 VARCHAR(100), data15 VARCHAR(100), data16 VARCHAR(100));
        |create table thing(id VARCHAR(100) PRIMARY KEY, person_id VARCHAR(100), name VARCHAR(100), data1 VARCHAR(100), data2 VARCHAR(100), data3 VARCHAR(100), data4 VARCHAR(100), data5 VARCHAR(100), data6 VARCHAR(100), data7 VARCHAR(100), data8 VARCHAR(100), data9 VARCHAR(100), data10 VARCHAR(100), data11 VARCHAR(100), data12 VARCHAR(100), data13 VARCHAR(100), data14 VARCHAR(100), data15 VARCHAR(100), data16 VARCHAR(100));
        |""".stripMargin
    statement.execute(sql)
    statement.close()
  })

}