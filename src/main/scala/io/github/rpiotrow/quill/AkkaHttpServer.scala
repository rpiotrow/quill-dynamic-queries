package io.github.rpiotrow.quill

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import io.github.rpiotrow.quill.domain._
import spray.json.DefaultJsonProtocol
import sttp.client._
import sttp.model.Uri

import java.util.Date
import scala.concurrent.ExecutionContext

trait JsonProtocol extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val jsonFormatOrganization = jsonFormat18(Organization)
  implicit val jsonFormatPerson = jsonFormat19(Person)
  implicit val jsonFormatThing = jsonFormat19(Thing)
}
object JsonProtocol extends JsonProtocol

object AkkaHttpServer {

  implicit private val sttpBackend = HttpURLConnectionBackend()

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext = system.executionContext

    Http().newServerAt("localhost", 8080).bind(route())

    println(s"Server online at http://localhost:8080/")

    List.range(1, 10).foreach(index =>
      requestWithTime(s"request with static query $index", uri"http://localhost:8080/quill/static")
    )
    List.range(1, 10).foreach(index =>
      requestWithTime(s"request with dynamic query $index", uri"http://localhost:8080/quill/dynamic")
    )
  }

  private def requestWithTime(name: String, uri: Uri): Unit = {
    val start = new Date().getTime
    basicRequest.get(uri"$uri").send().body match {
      case Left(error) => println(s"ERROR $error")
      case Right(payload) => () // println(payload)
    }
    val stop = new Date().getTime
    println(s"time of $name: ${stop-start}ms")
  }

  private def route()(implicit ec: ExecutionContext) = {
    val repository = new Repository()
    val service = new AkkaService(repository)

    import JsonProtocol._

    pathPrefix("quill") {
      pathPrefix("dynamic") {
        get {
          complete(service.getOrganizationsForThingsDynamic)
        }
      } ~ pathPrefix("static") {
        get {
          complete(service.getOrganizationsForThingsStatic)
        }
      }
    }
  }

}
