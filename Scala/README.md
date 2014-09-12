# MongoDB with ReactiveMongo: a Sample App

This is a sample app to show in a few lines of code how MongoDB can be used to build a simple (yet full featured) web application.

This example use the following:
* MongoDB (*yeah, no kidding*)
* ReactiveMongo, a non-blocking and asynchronous Scala driver for MongoDB
* Play 2.1 as a web framework
* Play ReactiveMongo plugin

This application manages articles. An article has a title, a text content and a publisher. The articles can be updated and sorted by title, publisher, creation/update date, etc. One or more attachments can be uploaded and bound to an article (like an image, a pdf, an archive...). All the classic CRUD operations are implemented.

To sum up, this sample covers the following features of MongoDB:
* Simple queries
* Sorting the results of a query
* Update
* Delete
* GridFS a storage engine for the attachments

The following features of ReactiveMongo driver are covered:
* (Non-blocking) queries, updates, deletes
* (Non-blocking) GridFS storage
* Streaming files from and into GridFS

## A Glimpse at MongoDB Features with ReactiveMongo
This application uses some concepts from MongoDB that we will explain in this section.

### A Word About MongoDB

> MongoDB (from "humongous") is a scalable, high-performance, open source NoSQL database.

It's a NoSQL database - in other words, MongoDB does not use SQL as a query language, and is not fully ACID-compliant. In fact, it's not even a relational database: Mongo stores data as *documents*, into buckets that are called *collections*.

A document is a set of data, in BSON (Binary JSON). This means that the manipulation of a document can be very easy, and that turning a document into plain text JSON is a piece of cake. A document that you get from MongoDB is quite ready to be used in a web context (like a web API).

As for data, queries are expressed in JSON style. It is very easy to write and read Mongo queries - you use the same format that you manipulate every day when you build web applications.

But the real power of MongoDB is that it is very scalable. It is very easy to add replicas (for replication, or for balancing eventual consistent read operations), and shards: when you get a high volume of data, you can set up a new server that will store only a part of the data. Mongo handles the query routing so that it is transparent for the developpers.

### Simple Query

Queries are written in BSON (binary JSON). An empty query matches all the documents that are stored in the collection.

```scala
val collection = db[BSONCollection]("articles")
// empty query will match all documents by default
val query = BSONDocument()
// run this query over the collection
val cursor = collection.find(query).cursor[BSONDocument]
// got the list of documents (in a fully non-blocking way)
val futureList = cursor.toList
```

If we want to get only the documents that have a field *publisher* which value is *Stephane*, we may just write the following query:

```scala
val query = BSONDocument("publisher" -> "Stephane")
// run this query over the collection
val articlesPublishedByStephane = collection.find(query).cursor[Article]
val futureListOfArticlesPublishedByStephane: Future[List[Article]] = articlesPublishedByStephane.toList
```

In JSON, the query would look like this:

```json
{
  "publisher": "Stephane"
}
```

If we just want to get the first matching document, we can use the `one` method which returns a future option:

```scala
val query = BSONDocument("publisher" -> "Stephane")
// run this query over the collection
val articlePublishedByStephane: Future[Option[Article]] = collection.find(query).one[Article]
```

### Sort a Query

Sorting is as easy as writing a query. In fact, we may just write the following (in JSON):

```json
{
  "$query": {
    "publisher": "Stephane"
  },
  "$orderby": {
    "creationDate": 1
  }
}
```

Which gives with ReactiveMongo:

```scala
// build a selection document with an empty query and a sort subdocument ('$orderby')
val query = BSONDocument(
  "$orderby" -> BSONDocument(
    "creationDate" -> 1
  ),
  "$query" -> BSONDocument(
    "publisher" -> "Stephane"
  )
)
```

The query is run this way:

```scala
val cursor = collection.find(query).cursor[Article] // a cursor over the results, deserialized as Article instances
// build (asynchronously) a list containing all the articles
val futureListOfArticles: Future[List[Article]] = cursor.toList
futureListOfArticles.onSuccess { articles =>
  for(article <- articles)
    println("found article: " + article)
}
```

This will find all the articles published by Stephane and order the results by the creationDate (the oldest comes first).
The original query is encapsulated in a `$query` subdocument, and the sort criteria is in an object named `$orderby`.

#### Sort using the query builder

There is a query builder that helps to make queries simpler, providing useful methods like `sort` and `projection`. We can also rewrite the previous snippet like this:

```scala
val cursor = collection.
               find(BSONDocument("publisher" -> "Stephane")).
               sort(BSONDocument("creationDate" -> 1)).
               cursor[Article] // a cursor over the results, deserialized as Article instances
// build (asynchronously) a list containing all the articles
val futureListOfArticles: Future[List[Article]] = cursor.toList
futureListOfArticles.onSuccess { articles =>
  for(article <- articles)
    println("found article: " + article)
}
```


### Update

Mongo allows two "standard" update modes:
* replacement - the given document may replace the matched documents
* modification - the given document is a *modifier* object (a document that has special operators in it, like `$set`, `$unset`, `$rename`, etc.)

Consider the following example (we modify an article that has a certain id):

```scala
val id = "50181f15e0f8477d00a5859e"
val objectId = BSONObjectID(id)
// create a modifier document, ie a document that contains the update operations to run onto the documents matching the query
val modifier = BSONDocument(
  // this modifier will set the fields 'updateDate', 'title', 'content', and 'publisher'
  "$set" -> BSONDocument(
    "updateDate" -> BSONDateTime(new DateTime().getMillis),
    "title" -> "a new title",
    "content" -> "a new text content",
    "publisher" -> "Jack")
)
// ok, let's do the update
collection.update(BSONDocument("_id" -> objectId), modifier).onComplete {
  case Failure(e) => throw e
  case Success(_) => println("successful!")
}
```

The modifier in pseudo-JSON would be:
```javascript
{
  "$set": {
    "updateDate": new Date(),
    "title": "a new title",
    "content": "a new text content",
    "publisher": "Jack"
  }
}
```

### Delete

Deletion is done the same way:

```scala
val id = "50181f15e0f8477d00a5859e"
collection.remove(BSONDocument("_id" -> BSONObjectID(id)).onComplete {
  case Failure(e) => throw e
  case Success(_) => println("successful!")
}
```

### GridFS

GridFS is a specification for storing large files in MongoDB. It allows to store potentially large files into MongoDB, with their metadata. Thus, it provides a file storage that can replicated and sharded like any other collection.

GridFS is very simple in its approach: the files are cut into chunks that are written into a fs.chunks collection. Their metadata are saved in a fs.files collection.

```javascript
// in the MongoDB console
> db.fs.files.find().limit(1)
{ "_id" : ObjectId("50181f15e0f8477d00a5859e"), "article" : ObjectId("50181efbe0f8477f00a5859d"), "chunkSize" : 262144, "contentType" : "application/octet-stream", "filename" : "archive.zip", "length" : 36018804, "uploadDate" : ISODate("2012-07-31T18:08:25.175Z") }
> db.fs.chunks.find({}, {data: 0}).limit(1) // we strip data :)
{ "_id" : ObjectId("50181f1806e0582d8ba37dea"), "files_id" : ObjectId("50181f15e0f8477d00a5859e"), "n" : 124}
```

ReactiveMongo allows to stream those files from and into GridFS, in a non-blocking way. Let's take a look to an example:

```scala
import java.io.{File, FileInputStream}
import reactivemongo.api.gridfs._

val name = "archive.zip"
val contentType = "application/octet-stream"
val gridFS = new GridFS(db, "attachments")

val fileToSave = DefaultFileToSave("archive.zip", Some(contentType))

// returns a future which is completed when the upload is done
val futureResult: Future[ReadFile[BSONValue]] = gridFS.writeFromInputStream(fileToSave new FileInputStream(new File(name)))
```

## About the Web Application

This web application uses all these features from MongoDB and ReactiveMongo. Obviously, they are adapated to fit the Play concepts - take a look to the code and start your own!

Author: [Stephane Godbillon](http://stephane.godbillon.com) - [Twitter](https://twitter.com/sgodbillon)
