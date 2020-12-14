package io.github.rpiotrow.quill

import java.util.Date
import scala.concurrent.ExecutionContext

object Main {

  def main(args: Array[String]): Unit = {

    implicit val ec = ExecutionContext.global

    val repository = new Repository()
    val service = new ServiceImpl(repository)

    List.range(1, 10).foreach(index =>
      invokeWithTime(s"static query executed for $index time", () => service.getOrganizationsForThingsStatic)
    )
    List.range(1, 10).foreach(index =>
      invokeWithTime(s"dynamic query executed for $index time", () => service.getOrganizationsForThingsDynamic)
    )
  }

  private def invokeWithTime(name: String, callback: () => Any): Unit = {
    val start = new Date().getTime
    callback()
    val stop = new Date().getTime
    println(s"time of $name: ${stop-start}ms")
  }

}
