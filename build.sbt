name := "scala.structs.algos"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.8.2-RC3"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "25.0.2-R37",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
