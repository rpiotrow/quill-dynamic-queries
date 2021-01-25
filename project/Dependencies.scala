import sbt._

object Dependencies {
  object Versions {
    val doobieV            = "0.10.0"
    val quillV             = "3.6.0"
  }

  lazy val quill = Seq(
    "org.tpolecat"             %% "doobie-core"                 % Versions.doobieV,
    "org.tpolecat"             %% "doobie-h2"                   % Versions.doobieV,
    "org.tpolecat"             %% "doobie-quill"                % Versions.doobieV
  )
}
