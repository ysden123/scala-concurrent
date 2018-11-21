import sbt.Keys.libraryDependencies

lazy val scalaLoggingVersion = "3.9.0"
lazy val logbackClassicVersion = "1.2.3"

lazy val commonSettings = Seq(
  organization := "com.stulsoft",
  version := "0.0.2",
  javacOptions ++= Seq("-source", "11"),
  scalaVersion := "2.12.7",
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps"),
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "ch.qos.logback" % "logback-classic" % logbackClassicVersion,
  )
)

lazy val chapter2 = (project in file("chapter2"))
	.settings(commonSettings: _*)
	.settings(
		name := "chapter2"
	)

lazy val chapter4 = (project in file("chapter4"))
	.settings(commonSettings: _*)
	.settings(
		name := "chapter4"
	)

lazy val chapter5 = (project in file("chapter5"))
	.settings(commonSettings: _*)
	.settings(
		name := "chapter5"
	)
