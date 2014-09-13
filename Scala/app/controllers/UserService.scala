package controllers

import scala.concurrent.Future

import models.entity.User
import models.response.UserResponse

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsError
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.mvc._
import play.api.mvc.Action
import play.api.mvc.BodyParsers
import play.api.mvc.Controller

import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.BSONFormats._

import reactivemongo.api.Cursor
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson.BSONDocument

object UserService extends Controller with MongoController {

  val collection = db[BSONCollection]("users")
  
  //implicit val testFormat = Json.format[Test]

  def create = Action(BodyParsers.parse.json) { implicit request =>

    val userResult = request.body.validate[User]
    userResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "ERROR", "message" -> JsError.toFlatJson(errors)))
      },
      user => {
        collection.save(User.UserEntityToBSONWriter.write(user))
        Ok(Json.obj("status" -> "OK", "message" -> ("User '" + user.email + "' saved.")))
      })
  }

  def getAll = Action.async { implicit request =>

    val found = collection.find(BSONDocument.empty).cursor[UserResponse].collect[List]()

    found.map { users => Ok(Json.toJson(users))
    }.recover {
      case e =>
        e.printStackTrace()
        BadRequest(e.getMessage())
    }

  }
  
   def getById = Action.async { implicit request =>

    val found = collection.find(BSONDocument()).cursor[UserResponse].collect[List]()

    found.map { users => Ok(Json.toJson(users))
    }.recover {
      case e =>
        e.printStackTrace()
        BadRequest(e.getMessage())
    }

  }

}