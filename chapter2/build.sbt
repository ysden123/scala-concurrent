organization := "com.stulsoft"
name := "scala-concurrent-chapter2"
version := "0.0.1"

lazy val scalaLoggingVersion = "3.9.0"
lazy val logbackClassicVersion = "1.2.3"

javacOptions ++= Seq("-source", "11")

scalaVersion in ThisBuild := "2.12.7"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
  "ch.qos.logback" % "logback-classic" % logbackClassicVersion
)

