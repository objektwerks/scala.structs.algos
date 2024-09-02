name := "scala.structs.algos"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.5.1-RC2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "22.0.0-R33",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
