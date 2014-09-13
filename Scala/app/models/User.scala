package models

import org.joda.time.DateTime
import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.JsPath
import play.api.libs.json.Reads
import reactivemongo.bson.BSONDateTime
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson.Producer.nameOptionValue2Producer
import reactivemongo.bson.Producer.nameValue2Producer
import play.api.libs.json.Writes
import play.api.libs.functional.syntax._
import play.modules.reactivemongo.json.BSONFormats._

object UserConst {

  val id = "id";
  val username = "username";
  val email = "email";
  val password = "password";
  val gender = "gender";
  val age = "age";
  val creationDate = "creationDate";
  val updateDate = "updateDate";

}

case class User(
  username: String,
  email: String,
  password: String,
  gender: String,
  age: Int)

object User {

  implicit val userFromJSon: Reads[User] = (
    (JsPath \ UserConst.username).read[String] and
    (JsPath \ UserConst.email).read[String] and
    (JsPath \ UserConst.password).read[String] and
    (JsPath \ UserConst.gender).read[String] and
    (JsPath \ UserConst.age).read[Int])(User.apply _)

  implicit val userToJSon: Writes[User] = (
    (JsPath \ UserConst.username).write[String] and
    (JsPath \ UserConst.email).write[String] and
    (JsPath \ UserConst.password).write[String] and
    (JsPath \ UserConst.gender).write[String] and
    (JsPath \ UserConst.age).write[Int])(unlift(User.unapply))

}

case class UserEntity(

  id: Option[BSONObjectID],
  username: String,
  email: String,
  password: String,
  gender: String,
  age: Int,
  creationDate: Option[DateTime],
  updateDate: Option[DateTime]) {

  def this(user: User) =
    this(
      Option.empty,
      username = user.username,
      email = user.email,
      password = user.password,
      gender = user.gender,
      age = user.age,
      Option.empty,
      Option.empty)
}

object UserEntity {

  implicit object UserBSONReader extends BSONDocumentReader[UserEntity] {
    def read(doc: BSONDocument): UserEntity = UserEntity(
      doc.getAs[BSONObjectID]("_id"),
      doc.getAs[String](UserConst.username).get,
      doc.getAs[String](UserConst.email).get,
      doc.getAs[String](UserConst.password).get,
      doc.getAs[String](UserConst.gender).get,
      doc.getAs[Int](UserConst.age).get,
      doc.getAs[BSONDateTime](UserConst.creationDate).map(dt => new DateTime(dt.value)),
      doc.getAs[BSONDateTime](UserConst.updateDate).map(dt => new DateTime(dt.value)))
  }

  implicit object UserBSONWriter extends BSONDocumentWriter[UserEntity] {
    def write(user: UserEntity): BSONDocument =
      BSONDocument(
        UserConst.id -> user.id.getOrElse(BSONObjectID.generate),
        UserConst.username -> user.username,
        UserConst.email -> user.email,
        UserConst.password -> user.password,
        UserConst.gender -> user.gender,
        UserConst.age -> user.age,
        UserConst.creationDate -> user.creationDate.map(date => BSONDateTime(date.getMillis)),
        UserConst.updateDate -> user.updateDate.map(date => BSONDateTime(date.getMillis)))
  }

  implicit val userEntityToJSon: Writes[UserEntity] = (
    (JsPath \ UserConst.id).write[Option[BSONObjectID]] and
    (JsPath \ UserConst.username).write[String] and
    (JsPath \ UserConst.email).write[String] and
    (JsPath \ UserConst.password).write[String] and
    (JsPath \ UserConst.gender).write[String] and
    (JsPath \ UserConst.age).write[Int] and
    (JsPath \ UserConst.creationDate).write[Option[DateTime]] and
    (JsPath \ UserConst.updateDate).write[Option[DateTime]] 
    )(unlift(UserEntity.unapply))

}