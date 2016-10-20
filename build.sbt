name := """portfolio-simulation"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.5",
  "log4j" % "log4j" % "1.2.17",
  "org.scalikejdbc" %% "scalikejdbc" % "2.4.2",
  "com.typesafe" % "config" % "1.3.0",
  "org.postgresql" % "postgresql" % "9.4.1209.jre7",
  "joda-time" % "joda-time" % "2.9.4",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "org.apache.poi" % "poi" % "3.15",
  "org.apache.poi" % "poi-ooxml" % "3.15",
  "org.scalikejdbc" %% "scalikejdbc-test"   % "2.4.2"   % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % Test,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")
