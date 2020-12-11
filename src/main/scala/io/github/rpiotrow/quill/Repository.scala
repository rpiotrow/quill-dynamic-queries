package io.github.rpiotrow.quill

import doobie.quill.DoobieContext
import doobie.ConnectionIO
import io.getquill.{idiom => _, _}
import io.getquill.SnakeCase
import io.github.rpiotrow.quill.domain._

class Repository {
  val ctx = new DoobieContext.H2(SnakeCase)
  import ctx._

  def getOrganizationsForThingsStatic(thingsNames: Seq[String]): ConnectionIO[Seq[Organization]] =
    run(
      querySchema[Organization]("organization")
        .join(query[Person]).on((organization, person) => organization.id == person.organizationId)
        .join(query[Thing]).on((organizationPerson, thing) => organizationPerson._2.id == thing.personId)
        .filter(tuple => liftQuery(thingsNames).contains(tuple._2.name))
        .map(_._1._1)
        .distinct
    ).map(_.toSeq)

  def getOrganizationsForThingsDynamic(thingsNames: Seq[String]): ConnectionIO[Seq[Organization]] =
    run(
      dynamicQuerySchema[Organization]("organization")
        .join(query[Person]).on((organization, person) => organization.id == person.organizationId)
        .join(query[Thing]).on((organizationPerson, thing) => organizationPerson._2.id == thing.personId)
        .filter(tuple => liftQuery(thingsNames).contains(tuple._2.name))
        .map(_._1._1)
        .distinct
    ).map(_.toSeq)
}
