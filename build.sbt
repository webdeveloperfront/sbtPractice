// TODO: si se desea ver las cosas hechas aqui , verificar la ultima rama de este capitulo

name := "preowned-kittens"

val gitHeadCommitSha = taskKey[String]("Deteerminta el commit actual de git - SHA")

val makeVersionProperties = taskKey[Seq[File]]("hacer una archivo llamado version.properties.")

def PreownedKittenProject(name: String): Project = (
  Project(name, file(name)).
    settings(
      scalaVersion  := "2.11.7",
      version       := "1.0",
      organization  := "com.preowned-kittens",
      libraryDependencies ++= Seq(
        "org.specs2" %% "specs2-core" % "3.7" % "test",
        "com.novocode" % "junit-interface" % "0.11" % "test"
      )
    )
  )

gitHeadCommitSha in ThisBuild := Process("git rev-parse HEAD").lines.head

lazy val common = (
  PreownedKittenProject("common")
    settings(
    makeVersionProperties := {
      val propFile = new File((resourceManaged in Compile). value, "version.properties")
      val content = "version=%s" format (gitHeadCommitSha.value)
      IO.write(propFile, content)
      Seq(propFile)
    },
    resourceGenerators in Compile <+= makeVersionProperties
    )
  )

lazy val analytics = (
  PreownedKittenProject("analytics")
    dependsOn(common)
    settings()
  )

lazy val website = (
  PreownedKittenProject("website")
    dependsOn(common)
    settings()
  )


