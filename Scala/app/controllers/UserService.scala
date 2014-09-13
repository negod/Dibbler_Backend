package controllers

import models.Article
import models.User
import models.UserEntity
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsError
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.mvc._
import play.api.mvc.Action
import play.api.mvc.BodyParsers
import play.api.mvc.Controller
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.BSONFormats._
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson.BSONDocument
import scala.concurrent.Future
import play.modules.reactivemongo.json.BSONFormats
import reactivemongo.api.Cursor

object UserService extends Controller with MongoController {

  val collection = db[BSONCollection]("UserEntitys")

  def create = Action(BodyParsers.parse.json) { implicit request =>

    val userResult = request.body.validate[User]
    userResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "ERROR", "message" -> JsError.toFlatJson(errors)))
      },
      user => {
        collection.save(UserEntity.UserBSONWriter.write(new UserEntity(user)))
        Ok(Json.obj("status" -> "OK", "message" -> ("User '" + user.email + "' saved.")))
      })
  }

  def getAll = Action.async { implicit request =>

    val found = collection.find(BSONDocument.empty).cursor[UserEntity].collect[List]()

    found.map { users => Ok(Json.toJson(users))
    }.recover {
      case e =>
        e.printStackTrace()
        BadRequest(e.getMessage())
    }

  }

}