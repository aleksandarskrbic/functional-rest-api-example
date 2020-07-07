import sbt._

object Dependencies {

  object Versions {
    val cats       = "2.1.1"
    val catsEffect = "2.1.3"
    val catsRetry  = "1.1.1"
    val circe      = "0.13.0"
    val ciris      = "1.1.1"
    val fs2        = "2.4.2"
    val http4s     = "0.21.3"
    val log4cats   = "1.1.1"
    val newtype    = "0.4.3"
    val refined    = "0.9.14"
    val doobie     = "0.8.8"
    val pureConfig = "0.13.0"

    val betterMonadicFor = "0.3.1"
    val kindProjector    = "0.11.0"
    val logback          = "1.2.3"

    val scalaCheck    = "1.14.3"
    val scalaTest     = "3.2.0"
    val scalaTestPlus = "3.2.0.0"
  }

  object Libraries {
    def circe(artifact: String): ModuleID  = "io.circe"     %% artifact % Versions.circe
    def ciris(artifact: String): ModuleID  = "is.cir"       %% artifact % Versions.ciris
    def http4s(artifact: String): ModuleID = "org.http4s"   %% artifact % Versions.http4s
    def doobie(artifact: String): ModuleID = "org.tpolecat" %% artifact % Versions.doobie

    val cats       = "org.typelevel"    %% "cats-core"   % Versions.cats
    val catsEffect = "org.typelevel"    %% "cats-effect" % Versions.catsEffect
    val catsRetry  = "com.github.cb372" %% "cats-retry"  % Versions.catsRetry
    val fs2        = "co.fs2"           %% "fs2-core"    % Versions.fs2

    val pureConfig     = "com.github.pureconfig" %% "pureconfig"             % Versions.pureConfig
    val pureConfigCats = "com.github.pureconfig" %% "pureconfig-cats-effect" % Versions.pureConfig

    val circeCore    = circe("circe-core")
    val circeGeneric = circe("circe-generic")
    val circeParser  = circe("circe-parser")
    val circeRefined = circe("circe-refined")

    val cirisCore    = ciris("ciris")
    val cirisEnum    = ciris("ciris-enumeratum")
    val cirisRefined = ciris("ciris-refined")

    val http4sDsl    = http4s("http4s-dsl")
    val http4sServer = http4s("http4s-blaze-server")
    val http4sClient = http4s("http4s-blaze-client")
    val http4sCirce  = http4s("http4s-circe")

    val refinedCore = "eu.timepit" %% "refined"      % Versions.refined
    val refinedCats = "eu.timepit" %% "refined-cats" % Versions.refined

    val doobieCore     = doobie("doobie-core")
    val doobieHikari   = doobie("doobie-hikari")
    val doobiePostgres = doobie("doobie-postgres")

    val log4cats = "io.chrisdavenport" %% "log4cats-slf4j" % Versions.log4cats
    val newtype  = "io.estatico"       %% "newtype"        % Versions.newtype

    // Compiler plugins
    val betterMonadicFor = "com.olegpy"    %% "better-monadic-for" % Versions.betterMonadicFor
    val kindProjector    = "org.typelevel" % "kind-projector"      % Versions.kindProjector

    // Runtime
    val logback = "ch.qos.logback" % "logback-classic" % Versions.logback

    // Test
    val scalaCheck    = "org.scalacheck"    %% "scalacheck"      % Versions.scalaCheck
    val scalaTest     = "org.scalatest"     %% "scalatest"       % Versions.scalaTest
    val scalaTestPlus = "org.scalatestplus" %% "scalacheck-1-14" % Versions.scalaTestPlus
  }

}
