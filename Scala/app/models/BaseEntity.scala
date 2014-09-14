package models

import org.joda.time.DateTime
import reactivemongo.bson.BSONDateTime
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import play.api.libs.json.Reads

abstract class BaseEntity {
  var extId = None: Option[String]
  var creationDate = None: Option[DateTime]
  var updateDate = None: Option[DateTime]
  var createdUser = None: Option[String]
  var updatedUser = None: Option[String]
}


  
  

