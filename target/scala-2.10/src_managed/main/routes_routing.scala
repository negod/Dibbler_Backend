// @SOURCE:E:/Programmering/Projekt/GeoMarket_Backend/GeoMarket_Backend/conf/routes
// @HASH:eb2b19c4208c4bbe12bfb024b73d000549083cc1
// @DATE:Sat Aug 23 19:07:15 CEST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:7
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:11
private[this] lazy val controllers_v1_EventService_getEvents1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/events"))))
        

// @LINE:12
private[this] lazy val controllers_v1_EventService_getEventById2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/event/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:16
private[this] lazy val controllers_Application_getAllCategories3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("categories"))))
        

// @LINE:17
private[this] lazy val controllers_Application_getAllCompanies4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("companies"))))
        

// @LINE:18
private[this] lazy val controllers_Application_getAllCompanyUsers5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("companyUsers"))))
        

// @LINE:19
private[this] lazy val controllers_Application_getAllEvents6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("events"))))
        

// @LINE:20
private[this] lazy val controllers_Application_getAllEventTexts7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("eventTexts"))))
        

// @LINE:21
private[this] lazy val controllers_Application_getAllEventTypes8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("eventTypes"))))
        

// @LINE:22
private[this] lazy val controllers_Application_getAllFilters9 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("filters"))))
        

// @LINE:23
private[this] lazy val controllers_Application_getAllFollowerEvents10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("followerEvents"))))
        

// @LINE:24
private[this] lazy val controllers_Application_getAllFollowerReq11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("followerReqs"))))
        

// @LINE:25
private[this] lazy val controllers_Application_getAllMovements12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("movements"))))
        

// @LINE:26
private[this] lazy val controllers_Application_getAllRoles13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles"))))
        

// @LINE:27
private[this] lazy val controllers_Application_getAllSessions14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sessions"))))
        

// @LINE:28
private[this] lazy val controllers_Application_getAllSettings15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("settings"))))
        

// @LINE:29
private[this] lazy val controllers_Application_getAllUsers16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users"))))
        

// @LINE:33
private[this] lazy val controllers_Assets_at17 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/events""","""controllers.v1.EventService.getEvents(lat:Double, lon:Double, radius:Int, lang:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/event/$id<[^/]+>""","""controllers.v1.EventService.getEventById(id:String, lang:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """categories""","""controllers.Application.getAllCategories()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """companies""","""controllers.Application.getAllCompanies()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """companyUsers""","""controllers.Application.getAllCompanyUsers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events""","""controllers.Application.getAllEvents()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """eventTexts""","""controllers.Application.getAllEventTexts()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """eventTypes""","""controllers.Application.getAllEventTypes()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """filters""","""controllers.Application.getAllFilters()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """followerEvents""","""controllers.Application.getAllFollowerEvents()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """followerReqs""","""controllers.Application.getAllFollowerReq()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """movements""","""controllers.Application.getAllMovements()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles""","""controllers.Application.getAllRoles()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sessions""","""controllers.Application.getAllSessions()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """settings""","""controllers.Application.getAllSettings()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users""","""controllers.Application.getAllUsers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:7
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ DEFAULT""", Routes.prefix + """"""))
   }
}
        

// @LINE:11
case controllers_v1_EventService_getEvents1(params) => {
   call(params.fromQuery[Double]("lat", None), params.fromQuery[Double]("lon", None), params.fromQuery[Int]("radius", None), params.fromQuery[String]("lang", None)) { (lat, lon, radius, lang) =>
        invokeHandler(controllers.v1.EventService.getEvents(lat, lon, radius, lang), HandlerDef(this, "controllers.v1.EventService", "getEvents", Seq(classOf[Double], classOf[Double], classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """v1/events"""))
   }
}
        

// @LINE:12
case controllers_v1_EventService_getEventById2(params) => {
   call(params.fromPath[String]("id", None), params.fromQuery[String]("lang", None)) { (id, lang) =>
        invokeHandler(controllers.v1.EventService.getEventById(id, lang), HandlerDef(this, "controllers.v1.EventService", "getEventById", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """v1/event/$id<[^/]+>"""))
   }
}
        

// @LINE:16
case controllers_Application_getAllCategories3(params) => {
   call { 
        invokeHandler(controllers.Application.getAllCategories(), HandlerDef(this, "controllers.Application", "getAllCategories", Nil,"GET", """ Get all of everything (Just test so see its working)""", Routes.prefix + """categories"""))
   }
}
        

// @LINE:17
case controllers_Application_getAllCompanies4(params) => {
   call { 
        invokeHandler(controllers.Application.getAllCompanies(), HandlerDef(this, "controllers.Application", "getAllCompanies", Nil,"GET", """""", Routes.prefix + """companies"""))
   }
}
        

// @LINE:18
case controllers_Application_getAllCompanyUsers5(params) => {
   call { 
        invokeHandler(controllers.Application.getAllCompanyUsers(), HandlerDef(this, "controllers.Application", "getAllCompanyUsers", Nil,"GET", """""", Routes.prefix + """companyUsers"""))
   }
}
        

// @LINE:19
case controllers_Application_getAllEvents6(params) => {
   call { 
        invokeHandler(controllers.Application.getAllEvents(), HandlerDef(this, "controllers.Application", "getAllEvents", Nil,"GET", """""", Routes.prefix + """events"""))
   }
}
        

// @LINE:20
case controllers_Application_getAllEventTexts7(params) => {
   call { 
        invokeHandler(controllers.Application.getAllEventTexts(), HandlerDef(this, "controllers.Application", "getAllEventTexts", Nil,"GET", """""", Routes.prefix + """eventTexts"""))
   }
}
        

// @LINE:21
case controllers_Application_getAllEventTypes8(params) => {
   call { 
        invokeHandler(controllers.Application.getAllEventTypes(), HandlerDef(this, "controllers.Application", "getAllEventTypes", Nil,"GET", """""", Routes.prefix + """eventTypes"""))
   }
}
        

// @LINE:22
case controllers_Application_getAllFilters9(params) => {
   call { 
        invokeHandler(controllers.Application.getAllFilters(), HandlerDef(this, "controllers.Application", "getAllFilters", Nil,"GET", """""", Routes.prefix + """filters"""))
   }
}
        

// @LINE:23
case controllers_Application_getAllFollowerEvents10(params) => {
   call { 
        invokeHandler(controllers.Application.getAllFollowerEvents(), HandlerDef(this, "controllers.Application", "getAllFollowerEvents", Nil,"GET", """""", Routes.prefix + """followerEvents"""))
   }
}
        

// @LINE:24
case controllers_Application_getAllFollowerReq11(params) => {
   call { 
        invokeHandler(controllers.Application.getAllFollowerReq(), HandlerDef(this, "controllers.Application", "getAllFollowerReq", Nil,"GET", """""", Routes.prefix + """followerReqs"""))
   }
}
        

// @LINE:25
case controllers_Application_getAllMovements12(params) => {
   call { 
        invokeHandler(controllers.Application.getAllMovements(), HandlerDef(this, "controllers.Application", "getAllMovements", Nil,"GET", """""", Routes.prefix + """movements"""))
   }
}
        

// @LINE:26
case controllers_Application_getAllRoles13(params) => {
   call { 
        invokeHandler(controllers.Application.getAllRoles(), HandlerDef(this, "controllers.Application", "getAllRoles", Nil,"GET", """""", Routes.prefix + """roles"""))
   }
}
        

// @LINE:27
case controllers_Application_getAllSessions14(params) => {
   call { 
        invokeHandler(controllers.Application.getAllSessions(), HandlerDef(this, "controllers.Application", "getAllSessions", Nil,"GET", """""", Routes.prefix + """sessions"""))
   }
}
        

// @LINE:28
case controllers_Application_getAllSettings15(params) => {
   call { 
        invokeHandler(controllers.Application.getAllSettings(), HandlerDef(this, "controllers.Application", "getAllSettings", Nil,"GET", """""", Routes.prefix + """settings"""))
   }
}
        

// @LINE:29
case controllers_Application_getAllUsers16(params) => {
   call { 
        invokeHandler(controllers.Application.getAllUsers(), HandlerDef(this, "controllers.Application", "getAllUsers", Nil,"GET", """""", Routes.prefix + """users"""))
   }
}
        

// @LINE:33
case controllers_Assets_at17(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     