name := """portfolio-simulation"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc-test"   % "2.4.2"   % "test",
  "org.scalikejdbc" %% "scalikejdbc" % "2.4.2",
  "com.typesafe" % "config" % "1.3.0",
  "org.postgresql" % "postgresql" % "9.4.1209.jre7",
  "joda-time" % "joda-time" % "2.9.4",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % Test,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")
