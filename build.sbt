ThisBuild / organization := "com.lightbend"

lazy val exampleScalaVersion = "2.12.7"
lazy val sbtreactiveappVersion = "1.5.0"
lazy val reactiveLibVersion = "0.9.2"
lazy val reactiveCliVersion = "1.3.1"

lazy val root = (project in file("."))
  .enablePlugins(ParadoxPlugin, ParadoxSitePlugin, ScriptedPlugin)
  .settings(
    name := "lightbend-orchestration-kubernetes-docs",
    version := "",
    resolvers += Resolver.bintrayRepo("typesafe", "internal-maven-releases"),
    sourceDirectory in Paradox := sourceDirectory.value / "main" / "paradox",
    sourceDirectory in (Paradox, paradoxTheme) := sourceDirectory.value / "main" / "paradox" / "_template",
    paradoxProperties in Compile ++= Map(
      "scalaVersion"   -> exampleScalaVersion,
      "sbtreactiveapp" -> sbtreactiveappVersion,
      "reactivelib"    -> reactiveLibVersion,
      "reactivecli"    -> reactiveCliVersion
    ),
    paradoxProperties in Paradox := (paradoxProperties in Compile).value,
    previewFixedPort := Some(8000),
    run := {
      previewSite.value
    },
    scriptedLaunchOpts := { scriptedLaunchOpts.value ++
      Seq("-Xmx1024M", "-Dplugin.version=" + sbtreactiveappVersion)
    },
    publish / skip := true,
  )
