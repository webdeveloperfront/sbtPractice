
name := "preowned-kittens"

//esta es la dependencia SPECS2 y jUnit para usar con SBT (la ultima version)
// 13:00 se comenta ya que se sigue lo q dice el libro parte 3,6
//libraryDependencies ++= Seq(
//  "org.specs2" %% "specs2-core" % "3.7" % "test",
//  "com.novocode" % "junit-interface" % "0.11" % "test"
//)


// aqui obtengo la version de GIT con la que estoy trabajando
val gitHeadCommitSha = taskKey[String]("Deteerminta el commit actual de git - SHA")


//si ejecutamos:
// > show gitHeadCommitSha
// veremos como pilla la version (commit) hecha en GitHub y la imprime


// se define para poder crear un fichero fisico
val makeVersionProperties = taskKey[Seq[File]]("hacer una archivo llamado version.properties.")


// 3,6 Putting it all together
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

// lo movemos para que ver como queda segun el libro, (parte 3,6) de manera definitiva
gitHeadCommitSha in ThisBuild := Process("git rev-parse HEAD").lines.head

//lo quitamos de aqui, por lo q dice el libro 3,6,. Esto se hace porque ahora mismo como tenemos
// subModulos, pues para ese capitulo definiremos la creacion del fichero de version (fisico en la ruta que ya sabemos:
// ..sbtPractice/target/scala-2.11/resource_managed/main/version.properties
//que actualmente esta definido para TODO el projecto; pues pasara a ser parte solo del subproyecto "common"
/*
makeVersionProperties := {
  val propFile = new File((resourceManaged in Compile). value, "version.properties")
  val content = "version=%s" format (gitHeadCommitSha.value)
  IO.write(propFile, content)
  Seq(propFile)
}
*/

//si ejecutamos:
// > show makeVersionProperties
// veremos que crea un archivo en la ruta:
// ..sbtPractice/target/scala-2.11/resource_managed/main/version.properties
// y graba la version que hace un momento lo vimos con "gitHeadCommitSha"





// 3,5 Defining with subprojects
// creamos el subProyecto common y le agregamos las librerias en este caso de test
// para poder con el codigo hecho en chap2, pues ejecutar los test q estan ahora mismo en common
lazy val common = (
  // quitamos esto para usar una funcion definida lineas arriba (paso 3,6)
  // Project("common", file("common"))
  PreownedKittenProject("common")
    settings(
      makeVersionProperties := {
        val propFile = new File((resourceManaged in Compile). value, "version.properties")
        val content = "version=%s" format (gitHeadCommitSha.value)
        IO.write(propFile, content)
        Seq(propFile)
      },

      // se mueve esto aqui para que solo valga para el proyecto common.
      resourceGenerators in Compile <+= makeVersionProperties
    )

  //se quita por lo dice libro parte 3,6
  /*
  settings(
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "3.7" % "test"
    )
  )
  */
  )


lazy val analytics = (
  // quitamos esto para usar una funcion definida lineas arriba (paso 3,6)
  // Project("analytics", file("analytics"))
  PreownedKittenProject("analytics")
    dependsOn(common)
    settings()
  )


lazy val website = (
  // quitamos esto para usar una funcion definida lineas arriba (paso 3,6)
  // Project("website", file("website"))
  PreownedKittenProject("website")
    dependsOn(common)
    settings()
  )


