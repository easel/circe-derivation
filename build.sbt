name := "circe-derivation-fail"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= {
  //
  Seq(
    "io.circe" %% "circe-core" % "0.2.0",
    "io.circe" %% "circe-generic" % "0.2.0",
    "io.circe" %% "circe-jawn" % "0.2.0"
  )
}


