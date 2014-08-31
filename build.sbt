name := "GeoMarket_Backend"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.modelmapper" % "modelmapper" % "0.3.5",  
  cache
)     

ebeanEnabled := true

play.Project.playJavaSettings


