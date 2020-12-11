import Dependencies._

// ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / scalaVersion     := "2.12.12"
ThisBuild / version          := "0.0.1-SNAPSHOT"
ThisBuild / organization     := "io.github.rpiotrow"

// workaround for sbt >= sbt.version=1.3.5 (see: https://github.com/sbt/sbt/issues/5410)
ThisBuild / fork in run      := true

lazy val root = (project in file("."))
  .settings(
    name := "quill-dynamic-queries",
    description := "Check dynamic queries in quill, doobie, akka",
    libraryDependencies ++= (cats ++ logging ++ quill ++ akkaHttp ++ sttp)
  )
