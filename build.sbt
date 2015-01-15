name := "pickmeupcar"

version := "1.0"

lazy val `pickmeupcar` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs,
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.webjars" % "jquery" % "2.1.3",
  "org.webjars"   %% "webjars-play"  % "2.3.0-2",
  "org.webjars" % "bootstrap-datepicker" % "1.3.1",
  "org.webjars" % "tarruda-bootstrap-datetimepicker" % "0.0.11")



unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  