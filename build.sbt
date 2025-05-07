name := "scala.structs.algos"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.6.4" // Scala 3.7.1-RC1 breaks ScalaFx!
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "24.0.0-R35",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
