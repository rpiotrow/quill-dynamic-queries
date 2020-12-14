import sbt._

object Dependencies {
  object Versions {
    val doobieV            = "0.9.4"
  }

  lazy val quill = Seq(
    "org.tpolecat"             %% "doobie-core"                 % Versions.doobieV,
    "org.tpolecat"             %% "doobie-h2"                   % Versions.doobieV,
    "org.tpolecat"             %% "doobie-hikari"               % Versions.doobieV,
    "org.tpolecat"             %% "doobie-quill"                % Versions.doobieV
  )
}
