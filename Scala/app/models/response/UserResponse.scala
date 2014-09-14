package models.response

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.JsPath
import reactivemongo.bson.BSONDateTime
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONObjectID
import play.api.libs.json.Writes
import play.api.libs.functional.syntax._
import models.constants.UserConst


case class UserResponse(
  extId: String,  
  username: String,
  email: String,
  gender: String,
  age: Int)

object UserResponse {

  implicit val userResponseToJSon: Writes[UserResponse] = (
    (JsPath \ UserConst.extId).write[String] and
    (JsPath \ UserConst.username).write[String] and
    (JsPath \ UserConst.email).write[String] and
    (JsPath \ UserConst.gender).write[String] and
    (JsPath \ UserConst.age).write[Int])(unlift(UserResponse.unapply))

  implicit object UserResponseBSONReader extends BSONDocumentReader[UserResponse] {
    def read(doc: BSONDocument): UserResponse = UserResponse(
      doc.getAs[String](UserConst.extId).get, 
      doc.getAs[String](UserConst.username).get, 
      doc.getAs[String](UserConst.email).get, 
      doc.getAs[String](UserConst.gender).get,
      doc.getAs[Int](UserConst.age).get)
  }

}