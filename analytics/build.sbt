libraryDependencies += "org.specs2" %% "specs2-core" % "3.7" % "test"

libraryDependencies += "org.specs2" %% "specs2-html" % "3.7" % "test"

//libraryDependencies += "de.vorb" % "pandoc" % "0.0.+"
libraryDependencies += "de.vorb" % "pandoc_2.10" % "0.2.0" % "test"

libraryDependencies += "org.pegdown" % "pegdown" % "1.6.0" % "test"

testOptions in Test += Tests.Argument("html")
