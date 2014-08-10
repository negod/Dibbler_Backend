name := "GeoMarket_Backend"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "mysql" % "mysql-connector-java" % "5.1.18",
  cache
)     

ebeanEnabled := true

play.Project.playJavaSettings

