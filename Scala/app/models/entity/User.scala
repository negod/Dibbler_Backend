package models.entity

import org.joda.time.DateTime
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.JsPath
import play.api.libs.json.Reads
import reactivemongo.bson.BSONDateTime
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONObjectID
import play.api.libs.functional.syntax._
import play.modules.reactivemongo.json.BSONFormats._
import models.constants.UserConst
import models.BaseEntity

case class User (
  username: String,
  email: String,
  password: String,
  gender: String,
  age: Int) extends BaseEntity

object User {
  
   implicit object UserEntityToBSONWriter extends BSONDocumentWriter[User] {
    def write(user: User): BSONDocument =
      BSONDocument(
        UserConst.username -> user.username,
        UserConst.email -> user.email,
        UserConst.password -> user.password,
        UserConst.gender -> user.gender,
        UserConst.age -> user.age,
        UserConst.creationDate -> BSONDateTime(new DateTime().getMillis),
        UserConst.updatedDate -> BSONDateTime(new DateTime().getMillis))
  }

  implicit val jSonToUserEntity: Reads[User] = (
    (JsPath \ UserConst.username).read[String] and
    (JsPath \ UserConst.email).read[String] and
    (JsPath \ UserConst.password).read[String] and
    (JsPath \ UserConst.gender).read[String] and
    (JsPath \ UserConst.age).read[Int])(User.apply _)

}