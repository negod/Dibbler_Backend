// @SOURCE:E:/Programmering/Projekt/GeoMarket_Backend/GeoMarket_Backend/conf/routes
// @HASH:eb2b19c4208c4bbe12bfb024b73d000549083cc1
// @DATE:Sat Aug 23 19:07:15 CEST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:12
// @LINE:11
package controllers.v1 {

// @LINE:12
// @LINE:11
class ReverseEventService {
    

// @LINE:12
def getEventById(id:String, lang:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "v1/event/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)) + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("lang", lang)))))
}
                                                

// @LINE:11
def getEvents(lat:Double, lon:Double, radius:Int, lang:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "v1/events" + queryString(List(Some(implicitly[QueryStringBindable[Double]].unbind("lat", lat)), Some(implicitly[QueryStringBindable[Double]].unbind("lon", lon)), Some(implicitly[QueryStringBindable[Int]].unbind("radius", radius)), Some(implicitly[QueryStringBindable[String]].unbind("lang", lang)))))
}
                                                
    
}
                          
}
                  

// @LINE:33
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:7
package controllers {

// @LINE:33
class ReverseAssets {
    

// @LINE:33
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:7
class ReverseApplication {
    

// @LINE:16
def getAllCategories(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "categories")
}
                                                

// @LINE:18
def getAllCompanyUsers(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "companyUsers")
}
                                                

// @LINE:21
def getAllEventTypes(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "eventTypes")
}
                                                

// @LINE:19
def getAllEvents(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "events")
}
                                                

// @LINE:24
def getAllFollowerReq(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "followerReqs")
}
                                                

// @LINE:22
def getAllFilters(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "filters")
}
                                                

// @LINE:20
def getAllEventTexts(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "eventTexts")
}
                                                

// @LINE:28
def getAllSettings(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "settings")
}
                                                

// @LINE:25
def getAllMovements(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "movements")
}
                                                

// @LINE:23
def getAllFollowerEvents(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "followerEvents")
}
                                                

// @LINE:27
def getAllSessions(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "sessions")
}
                                                

// @LINE:26
def getAllRoles(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "roles")
}
                                                

// @LINE:29
def getAllUsers(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "users")
}
                                                

// @LINE:7
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:17
def getAllCompanies(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "companies")
}
                                                
    
}
                          
}
                  


// @LINE:12
// @LINE:11
package controllers.v1.javascript {

// @LINE:12
// @LINE:11
class ReverseEventService {
    

// @LINE:12
def getEventById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.v1.EventService.getEventById",
   """
      function(id,lang) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/event/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("lang", lang)])})
      }
   """
)
                        

// @LINE:11
def getEvents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.v1.EventService.getEvents",
   """
      function(lat,lon,radius,lang) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/events" + _qS([(""" + implicitly[QueryStringBindable[Double]].javascriptUnbind + """)("lat", lat), (""" + implicitly[QueryStringBindable[Double]].javascriptUnbind + """)("lon", lon), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("radius", radius), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("lang", lang)])})
      }
   """
)
                        
    
}
              
}
        

// @LINE:33
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:7
package controllers.javascript {

// @LINE:33
class ReverseAssets {
    

// @LINE:33
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:7
class ReverseApplication {
    

// @LINE:16
def getAllCategories : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllCategories",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "categories"})
      }
   """
)
                        

// @LINE:18
def getAllCompanyUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllCompanyUsers",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "companyUsers"})
      }
   """
)
                        

// @LINE:21
def getAllEventTypes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllEventTypes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "eventTypes"})
      }
   """
)
                        

// @LINE:19
def getAllEvents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllEvents",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "events"})
      }
   """
)
                        

// @LINE:24
def getAllFollowerReq : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllFollowerReq",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "followerReqs"})
      }
   """
)
                        

// @LINE:22
def getAllFilters : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllFilters",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "filters"})
      }
   """
)
                        

// @LINE:20
def getAllEventTexts : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllEventTexts",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "eventTexts"})
      }
   """
)
                        

// @LINE:28
def getAllSettings : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllSettings",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "settings"})
      }
   """
)
                        

// @LINE:25
def getAllMovements : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllMovements",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "movements"})
      }
   """
)
                        

// @LINE:23
def getAllFollowerEvents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllFollowerEvents",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "followerEvents"})
      }
   """
)
                        

// @LINE:27
def getAllSessions : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllSessions",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sessions"})
      }
   """
)
                        

// @LINE:26
def getAllRoles : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllRoles",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "roles"})
      }
   """
)
                        

// @LINE:29
def getAllUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllUsers",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
      }
   """
)
                        

// @LINE:7
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:17
def getAllCompanies : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllCompanies",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "companies"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:12
// @LINE:11
package controllers.v1.ref {


// @LINE:12
// @LINE:11
class ReverseEventService {
    

// @LINE:12
def getEventById(id:String, lang:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.v1.EventService.getEventById(id, lang), HandlerDef(this, "controllers.v1.EventService", "getEventById", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """v1/event/$id<[^/]+>""")
)
                      

// @LINE:11
def getEvents(lat:Double, lon:Double, radius:Int, lang:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.v1.EventService.getEvents(lat, lon, radius, lang), HandlerDef(this, "controllers.v1.EventService", "getEvents", Seq(classOf[Double], classOf[Double], classOf[Int], classOf[String]), "GET", """""", _prefix + """v1/events""")
)
                      
    
}
                          
}
        

// @LINE:33
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:7
package controllers.ref {


// @LINE:33
class ReverseAssets {
    

// @LINE:33
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:7
class ReverseApplication {
    

// @LINE:16
def getAllCategories(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllCategories(), HandlerDef(this, "controllers.Application", "getAllCategories", Seq(), "GET", """ Get all of everything (Just test so see its working)""", _prefix + """categories""")
)
                      

// @LINE:18
def getAllCompanyUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllCompanyUsers(), HandlerDef(this, "controllers.Application", "getAllCompanyUsers", Seq(), "GET", """""", _prefix + """companyUsers""")
)
                      

// @LINE:21
def getAllEventTypes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllEventTypes(), HandlerDef(this, "controllers.Application", "getAllEventTypes", Seq(), "GET", """""", _prefix + """eventTypes""")
)
                      

// @LINE:19
def getAllEvents(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllEvents(), HandlerDef(this, "controllers.Application", "getAllEvents", Seq(), "GET", """""", _prefix + """events""")
)
                      

// @LINE:24
def getAllFollowerReq(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllFollowerReq(), HandlerDef(this, "controllers.Application", "getAllFollowerReq", Seq(), "GET", """""", _prefix + """followerReqs""")
)
                      

// @LINE:22
def getAllFilters(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllFilters(), HandlerDef(this, "controllers.Application", "getAllFilters", Seq(), "GET", """""", _prefix + """filters""")
)
                      

// @LINE:20
def getAllEventTexts(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllEventTexts(), HandlerDef(this, "controllers.Application", "getAllEventTexts", Seq(), "GET", """""", _prefix + """eventTexts""")
)
                      

// @LINE:28
def getAllSettings(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllSettings(), HandlerDef(this, "controllers.Application", "getAllSettings", Seq(), "GET", """""", _prefix + """settings""")
)
                      

// @LINE:25
def getAllMovements(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllMovements(), HandlerDef(this, "controllers.Application", "getAllMovements", Seq(), "GET", """""", _prefix + """movements""")
)
                      

// @LINE:23
def getAllFollowerEvents(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllFollowerEvents(), HandlerDef(this, "controllers.Application", "getAllFollowerEvents", Seq(), "GET", """""", _prefix + """followerEvents""")
)
                      

// @LINE:27
def getAllSessions(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllSessions(), HandlerDef(this, "controllers.Application", "getAllSessions", Seq(), "GET", """""", _prefix + """sessions""")
)
                      

// @LINE:26
def getAllRoles(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllRoles(), HandlerDef(this, "controllers.Application", "getAllRoles", Seq(), "GET", """""", _prefix + """roles""")
)
                      

// @LINE:29
def getAllUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllUsers(), HandlerDef(this, "controllers.Application", "getAllUsers", Seq(), "GET", """""", _prefix + """users""")
)
                      

// @LINE:7
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ DEFAULT""", _prefix + """""")
)
                      

// @LINE:17
def getAllCompanies(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllCompanies(), HandlerDef(this, "controllers.Application", "getAllCompanies", Seq(), "GET", """""", _prefix + """companies""")
)
                      
    
}
                          
}
        
    