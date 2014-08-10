package controllers;



import java.util.List;

import models.Category;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result getCategory() {
    	List<Category> categories = Category.find.all();    	
    	return ok(Json.toJson(categories));
    }

}
