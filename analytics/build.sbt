// SPECS2

libraryDependencies += "org.specs2" %% "specs2-core" % "3.7" % "test"

libraryDependencies += "org.specs2" %% "specs2-html" % "3.7" % "test"

libraryDependencies += "de.vorb" % "pandoc_2.10" % "0.2.0" % "test"

libraryDependencies += "org.pegdown" % "pegdown" % "1.6.0" % "test"

//esta solucion es dada por stackOverflow
testOptions += Tests.Argument(TestFrameworks.Specs2, "html", "html.outdir", "target/generated/test-reports")

//testOptions += Tests.Argument(TestFrameworks.Specs2, "html")
//javaOptions in Test += "-Dspecs2.outDir=" + (target.value / "generated/test-reports").getAbsolutePath

fork in Test := true


// JUNIT


// SCALA_CHECK

