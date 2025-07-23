name := "scala.structs.algos"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.7.2-RC2"
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "24.0.2-R36",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all",
  // Silences 3.7.0+ implicit using warnings:
  "-Wconf:msg=Implicit parameters should be provided with a `using` clause:s"
)
