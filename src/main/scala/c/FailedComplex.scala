package c

import io.circe.generic.semiauto._

/*
 * This demonstrates failures trying to derive members of a sealed trait,
 * ostensibly due to SI-7046.
 */
sealed trait A {
  def name: String
}

sealed trait B extends A {
  def id: Int
}

case class B1(name: String, id: Int) extends B

case class B2(name: String, id: Int, alt: String) extends B

case class B3(name: String, id: Int, v: Long) extends B {
  def toJson = {
    val thisAsA: A = this
    A.encoder.apply(thisAsA)
  }
}

/*
 * Fails to compile usage of derivation on the case class B3
 *
 * [error] /Users/erik/Projects/circe-derivation-fail/src/main/scala/c/FailedComplex.scala:35: could not find implicit value for parameter encode: io.circe.generic.encoding.DerivedObjectEncoder[R]
 * [error]   implicit val encoder = deriveFor[A].encoder
 * [error]                                       ^
 */
object A {
  implicit val encoder = deriveFor[A].encoder
}