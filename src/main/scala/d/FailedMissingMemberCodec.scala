package d

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

case class Id(id: Long) extends AnyVal

sealed trait TestADT

case class FailedMissingMemberCodec(id: Option[Id])
  extends TestADT

object FailedMissingMemberCodec {

  object FailedDerivation {
    // Prior to b883d8a0e, the encoder for "Id" was not necessary
    //  implicit val testJobIdEncoder: Encoder[Id] = Encoder[Long].contramap(_.id)
    //  implicit val testJobIdDecoder: Decoder[Id] = Decoder[Long].map(Id)
    implicit val ssTestEncoder = deriveFor[TestADT].encoder
    implicit val ssTestDecoder = deriveFor[TestADT].decoder
  }

  object SuccessfulDerivation {
    // Properly including the member codecs works properly on both versions
    implicit val testJobIdEncoder: Encoder[Id] = Encoder[Long].contramap(_.id)
    implicit val testJobIdDecoder: Decoder[Id] = Decoder[Long].map(Id)
    implicit val ssTestEncoder = deriveFor[TestADT].encoder
    implicit val ssTestDecoder = deriveFor[TestADT].decoder
  }
}
