import Dependencies._

name := "pure-fp-rest-api"
version := "0.1"
scalaVersion := "2.13.3"

lazy val root = (project in file("."))
  .enablePlugins(DockerPlugin)
  .enablePlugins(AshScriptPlugin)
  .settings(
    name := "trip-api",
    packageName in Docker := "trip-api",
    scalafmtOnCompile := true,
    resolvers += Resolver.sonatypeRepo("snapshots"),
    scalacOptions ++= Seq("-language:higherKinds"),
    dockerBaseImage := "openjdk:8u201-jre-alpine3.9",
    dockerExposedPorts ++= Seq(8080),
    makeBatScripts := Seq(),
    dockerUpdateLatest := true,
    libraryDependencies ++= Seq(
          compilerPlugin(Libraries.kindProjector cross CrossVersion.full),
          compilerPlugin(Libraries.betterMonadicFor),
          Libraries.cats,
          Libraries.catsEffect,
          Libraries.catsRetry,
          Libraries.circeCore,
          Libraries.circeGeneric,
          Libraries.circeParser,
          Libraries.circeRefined,
          Libraries.fs2,
          Libraries.http4sDsl,
          Libraries.http4sServer,
          Libraries.http4sClient,
          Libraries.http4sCirce,
          Libraries.log4cats,
          Libraries.logback % Runtime,
          Libraries.newtype,
          Libraries.refinedCore,
          Libraries.refinedCats,
          Libraries.doobieCore,
          Libraries.doobieHikari,
          Libraries.doobiePostgres,
          Libraries.pureConfig,
          Libraries.pureConfigCats
        )
  )
