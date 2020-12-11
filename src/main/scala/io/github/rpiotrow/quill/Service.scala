package io.github.rpiotrow.quill

import io.github.rpiotrow.quill.domain.Organization

trait Service[F[_]] {
  def getOrganizationsForThingsDynamic: F[Seq[Organization]]
  def getOrganizationsForThingsStatic: F[Seq[Organization]]
}
