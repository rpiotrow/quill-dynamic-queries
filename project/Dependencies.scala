import sbt._

object Dependencies {
  object Versions {
    val catsV              = "2.2.0"
    val akkaV              = "2.6.10"
    val akkaHttpV          = "10.2.1"
    val logbackV           = "1.2.3"
    val doobieV            = "0.9.4"
    val sttpV              = "2.2.9"
  }

  lazy val cats = Seq(
    "org.typelevel"            %% "cats-core"                   % Versions.catsV
  )
  lazy val logging = Seq(
    "ch.qos.logback"           % "logback-classic"              % Versions.logbackV
  )
  lazy val akkaHttp = Seq(
    "com.typesafe.akka"        %% "akka-slf4j"                  % Versions.akkaV,
    "com.typesafe.akka"        %% "akka-stream"                 % Versions.akkaV,
    "com.typesafe.akka"        %% "akka-actor-typed"            % Versions.akkaV,
    "com.typesafe.akka"        %% "akka-http"                   % Versions.akkaHttpV,
    "com.typesafe.akka"        %% "akka-http-spray-json"        % Versions.akkaHttpV
  )
  lazy val quill = Seq(
    "org.tpolecat"             %% "doobie-core"                 % Versions.doobieV,
    "org.tpolecat"             %% "doobie-h2"                   % Versions.doobieV,
    "org.tpolecat"             %% "doobie-hikari"               % Versions.doobieV,
    "org.tpolecat"             %% "doobie-quill"                % Versions.doobieV
  )
  lazy val sttp = Seq(
    "com.softwaremill.sttp.client" %% "core" % Versions.sttpV
  )
}
