package io.github.rpiotrow.quill

import cats.effect.{ExitCode, IO}

import java.util.Date

object Main extends cats.effect.IOApp {

  def run(args: List[String]): IO[ExitCode] =
    ServiceImpl.create.use(service => IO {
      List.range(1, 10).foreach(index =>
        invokeWithTime(s"static query executed for $index time", () => service.getOrganizationsForThingsStatic)
      )
      List.range(1, 10).foreach(index =>
        invokeWithTime(s"dynamic query executed for $index time", () => service.getOrganizationsForThingsDynamic)
      )
    }).map(_ => ExitCode.Success)

  private def invokeWithTime(name: String, callback: () => Any): Unit = {
    val start = new Date().getTime
    callback()
    val stop = new Date().getTime
    println(s"time of $name: ${stop-start}ms")
  }

}
