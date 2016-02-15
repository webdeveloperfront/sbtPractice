libraryDependencies += "org.specs2" %% "specs2_2.11" % "3.7" % "test"

libraryDependencies += "org.pegdown" % "pegdown" % "1.6.0" % "test"

testOptions in Test += Tests.Argument("html")
