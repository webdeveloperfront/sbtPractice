name := "preowned-kittens"

organization := "com.preowned-kittens"

version := "1.0"

scalaVersion := "2.11.7"

//esta es la dependencia SPECS2 y jUnit para usar con SBT (la ultima version)
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.7" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)


// aqui obtengo la version de GIT con la que estoy trabajando
val gitHeadCommitSha = taskKey[String]("Deteerminta el commit actual de git - SHA")

gitHeadCommitSha := Process("git rev-parse HEAD").lines.head

//si ejecutamos:
// > show gitHeadCommitSha
// veremos como pilla la version (commit) hecha en GitHub y la imprime

val makeVersionProperties = taskKey[Seq[File]]("hacer una archivo llamado version.properties.")

makeVersionProperties := {
  val propFile = new File((resourceManaged in Compile). value, "version.properties")

  val content = "version=%s" format (gitHeadCommitSha.value)

  IO.write(propFile, content)

  Seq(propFile)
}

//si ejecutamos:
// > show makeVersionProperties
// veremos que crea un archivo en la ruta:
// ..sbtPractice/target/scala-2.11/resource_managed/main/version.properties
// y graba la version que hace un momento lo vimos con "gitHeadCommitSha"
