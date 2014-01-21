import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "interviewer"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    cache,
    "com.typesafe.play" %% "play-slick" % "0.5.0.8",
    "mysql" % "mysql-connector-java" % "5.1.18",
    "org.scala-lang" % "scala-compiler" % "2.10.3",
    "com.h2database" % "h2" % "1.3.166"

  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    scalaVersion := "2.10.3"
  )



}
