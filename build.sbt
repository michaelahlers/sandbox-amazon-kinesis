organization := "ahlers"

name := "sandbox-amazon-kinesis"

version := "1.0.0"

scalaVersion := "2.12.2"

/** See [[https://tpolecat.github.io/2017/04/25/scalac-flags.html ''Recommended Scalac Flags for 2.12'']] by [[https://tpolecat.github.io/about.html Rob Norris]]. */
scalacOptions :=
  "-Xcheckinit" ::
    "-Xfatal-warnings" ::
    "-Xlint:-unused,_" ::
    "-Ywarn-dead-code" ::
    "-Ywarn-inaccessible" ::
    "-Ywarn-infer-any" ::
    "-Ywarn-unused:-imports,-params,_" ::
    "-Ywarn-value-discard" ::
    "-deprecation" ::
    "-encoding" :: "utf-8" ::
    "-explaintypes" ::
    "-feature" ::
    "-unchecked" ::
    Nil

scalacOptions in(Compile, console) ~= (_ diff {
  "-Xfatal-warnings" ::
    Nil
})

cancelable in Global := true
