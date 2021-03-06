libraryDependencies ++=
  "ch.qos.logback" % "logback-classic" % "latest.release" ::
    "com.amazonaws" % "aws-java-sdk-kinesis" % "latest.release" ::
    "com.beachape" %% "enumeratum" % "latest.release" ::
    "com.iheart" %% "ficus" % "latest.release" ::
    "com.nrinaudo" %% "kantan.csv" % "latest.release" ::
    "com.nrinaudo" %% "kantan.csv-generic" % "latest.release" ::
    "com.typesafe" % "config" % "latest.release" ::
    "com.typesafe.play" %% "play-json" % "latest.release" ::
    "com.typesafe.scala-logging" %% "scala-logging" % "latest.release" ::
    "org.typelevel" %% "squants" % "latest.release" ::
    Nil

/** Satisfies a dependency of the Amazon Kinesis Java SDK. */
libraryDependencies ++=
  "com.fasterxml.jackson.core" % "jackson-core" % "latest.release" ::
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-cbor" % "latest.release" ::
    Nil
