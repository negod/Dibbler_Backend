package controllers

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
 
import play.api.Play.current
import play.modules.reactivemongo.ReactiveMongoPlugin
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentIdentity
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.core.commands.LastError

trait BaseService[E, R]{
  
	implicit val ec: ExecutionContext
	
	implicit val reader: BSONDocumentReader[E]
 
	val collectionName: String
 
	def db = ReactiveMongoPlugin.db
 
	def collection = db[BSONCollection](collectionName)
 
	def insert[E](document: E)(implicit writer: BSONDocumentWriter[E]): Future[LastError] = {
		collection.insert(document)
	}
 
	def findAll(implicit reader: BSONDocumentReader[R]): Future[List[R]] = {
		collection.find(BSONDocument()).cursor[R].collect[List]()
	}

}