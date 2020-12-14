package io.github.rpiotrow.quill

import io.github.rpiotrow.quill.domain.Organization

import scala.concurrent.{ExecutionContext, Future};

class ServiceImpl(repository: Repository)(implicit private val ec: ExecutionContext) extends BaseService(repository) with Service[Future] {

  override def getOrganizationsForThingsDynamic: Future[Seq[Organization]] =
    getOrganizationsForThingsDynamicIO.unsafeToFuture()
  override def getOrganizationsForThingsStatic: Future[Seq[Organization]] =
    getOrganizationsForThingsStaticIO.unsafeToFuture()

}
