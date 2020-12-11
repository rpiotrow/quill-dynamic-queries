package io.github.rpiotrow.quill

import cats.effect.{Blocker, ContextShift, IO}
import doobie.util.transactor.Transactor
import doobie.implicits._
import io.github.rpiotrow.quill.domain.Organization

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import org.h2.jdbcx.JdbcDataSource;

class BaseService(repository: Repository)(implicit private val ec: ExecutionContext) {

  implicit private val cs: ContextShift[IO] = IO.contextShift(ec)

  private val tnx: Transactor[IO] = {
    val blocker: Blocker = Blocker.liftExecutorService(Executors.newCachedThreadPool())
    val dataSource = createH2DataSource()

    val connection = dataSource.getConnection
    val statement = connection.createStatement
    val sql: String =
      """
        |create table organization(id VARCHAR(100) PRIMARY KEY, name VARCHAR(100), data1 VARCHAR(100), data2 VARCHAR(100), data3 VARCHAR(100), data4 VARCHAR(100), data5 VARCHAR(100), data6 VARCHAR(100), data7 VARCHAR(100), data8 VARCHAR(100), data9 VARCHAR(100), data10 VARCHAR(100), data11 VARCHAR(100), data12 VARCHAR(100), data13 VARCHAR(100), data14 VARCHAR(100), data15 VARCHAR(100), data16 VARCHAR(100));
        |create table person(id VARCHAR(100) PRIMARY KEY, organization_id VARCHAR(100), name VARCHAR(100), data1 VARCHAR(100), data2 VARCHAR(100), data3 VARCHAR(100), data4 VARCHAR(100), data5 VARCHAR(100), data6 VARCHAR(100), data7 VARCHAR(100), data8 VARCHAR(100), data9 VARCHAR(100), data10 VARCHAR(100), data11 VARCHAR(100), data12 VARCHAR(100), data13 VARCHAR(100), data14 VARCHAR(100), data15 VARCHAR(100), data16 VARCHAR(100));
        |create table thing(id VARCHAR(100) PRIMARY KEY, person_id VARCHAR(100), name VARCHAR(100), data1 VARCHAR(100), data2 VARCHAR(100), data3 VARCHAR(100), data4 VARCHAR(100), data5 VARCHAR(100), data6 VARCHAR(100), data7 VARCHAR(100), data8 VARCHAR(100), data9 VARCHAR(100), data10 VARCHAR(100), data11 VARCHAR(100), data12 VARCHAR(100), data13 VARCHAR(100), data14 VARCHAR(100), data15 VARCHAR(100), data16 VARCHAR(100));
        |""".stripMargin
    statement.execute(sql)
    statement.close()
    connection.close()

    Transactor.fromDataSource[IO](dataSource, ec, blocker)
  }

  private def createH2DataSource() = {
    val dataSource = new JdbcDataSource()
    dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
    dataSource.setUser("sa")
    dataSource.setPassword("")
    dataSource
  }

  protected def getOrganizationsForThingsDynamicIO: IO[Seq[Organization]] = {
    repository.getOrganizationsForThingsDynamic(Seq("thing1", "thing2"))
      .transact(tnx)
  }
  protected def getOrganizationsForThingsStaticIO: IO[Seq[Organization]] = {
    repository.getOrganizationsForThingsStatic(Seq("thing1", "thing2"))
      .transact(tnx)
  }
}
