name := "scala.structs.algos"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.3.0-RC6"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "20.0.0-R31",
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
