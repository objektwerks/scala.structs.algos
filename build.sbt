name := "scala.structs.algos"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.10.0"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "27.0.0-R39",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
