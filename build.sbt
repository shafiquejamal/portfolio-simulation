name := """portfolio-simulation"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.4",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")
