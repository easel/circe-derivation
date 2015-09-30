package b

import io.circe.generic.semiauto._

/*
 * This demonstrates failures trying to derive members of a sealed trait,
 * ostensibly due to SI-7046.
 */
sealed trait A {
  def name: String
}

trait B extends A

case class B1(name: String) extends B

/*
 * Fails to compile due to un-sealed trait in the hierarchy.
 *
 * /src/main/scala/b/FailedNonSealed.scala:21: could not find implicit value for parameter gen: shapeless.LabelledGeneric.Aux[b.A,R]
 * [error]   implicit val bEncoder = deriveFor[A].encoder
 */
object A {
  implicit val bEncoder = deriveFor[A].encoder
}