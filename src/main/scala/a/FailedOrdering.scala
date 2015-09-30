package a
import io.circe.generic.semiauto._

/*
 * This demonstrates failures trying to derive members of a sealed trait,
 * ostensibly due to SI-7046.
 */
sealed trait A {
  def name: String
}

/*
 * Fails to compile. Note that it is identical to B below, which succeeds
 *
 * circe-derivation-fail/src/main/scala/a/FailedComplex.scala:14: could not find implicit value for parameter gen: shapeless.LabelledGeneric.Aux[a.A,R]
 * [error]   implicit val aEncoder = deriveFor[A].encoder
 */
object A {
  implicit val aEncoder = deriveFor[A].encoder
}


case class A1(name: String) extends A

case class A2(name: String, other: Int) extends A

/*
 * Compiles successfully
 */
object B {
  implicit val bEncoder = deriveFor[A].encoder
}