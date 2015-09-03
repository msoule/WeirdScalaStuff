enablePlugins(ScalaJSPlugin)

name := "Scala.js Challenge"

scalaVersion := "2.11.5" // or any other Scala version >= 2.10.2

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"

skip in packageJSDependencies := false

jsDependencies += RuntimeDOM

testFrameworks += new TestFramework("utest.runner.Framework")

persistLauncher in Compile := true

persistLauncher in Test := false