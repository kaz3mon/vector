import mill._
import scalalib._

val defaultScalaVersion = "2.13.12"

val chiselVersion = "6.0.0-RC1"
val chiseltestVersion = "5.0.2"

val chiselIvy       = ivy"org.chipsalliance::chisel:$chiselVersion"
val chiselPluginIvy = ivy"org.chipsalliance:::chisel-plugin:$chiselVersion"
val chiseltestIvy   = ivy"edu.berkeley.cs::chiseltest:$chiseltestVersion"

object vector extends HasChiselTest with scalafmt.ScalafmtModule {
  override def millSourcePath = os.pwd / "hdl" / "chisel"
  override def moduleDeps = super.moduleDeps ++ Seq(
    // deps
  )
}

trait HasChisel extends ScalaModule {
  override def scalaVersion = defaultScalaVersion

  override def scalacOptions = super.scalacOptions() ++ Seq(
    "-unchecked",
    "-deprecation",
    "-language:reflectiveCalls",
    "-feature",
    "-Xcheckinit",
    "-Xfatal-warnings",
    "-Ywarn-dead-code",
    "-Ywarn-unused",
    "-Ymacro-annotations"
  )

  override def ivyDeps = super.ivyDeps() ++ Agg(chiselIvy)

  override def scalacPluginIvyDeps = super.scalacPluginIvyDeps() ++ Agg(chiselPluginIvy)
}

trait HasChiselTest extends HasChisel {
  object test extends ScalaTests with TestModule.ScalaTest {
    def ivyDeps = super.ivyDeps() ++ Agg(chiseltestIvy)
  }
}
