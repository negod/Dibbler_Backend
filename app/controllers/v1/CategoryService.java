package controllers.v1;

import java.util.List;

import constants.Language;

import mapper.event.CategoryMapper;
import models.Category;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class CategoryService extends Controller {

	public static Result getAllCategories(String language) {
		Logger.info("[Get ALL Categories: language =  " + language + "]");
		
		Language lang;
		try {
			lang = Language.valueOf(language);
		} catch (Exception e) {
			return Results.noContent();
		}
		
		List<Category> categories = Category.find.all();		
		return ok(Json.toJson(CategoryMapper.getInstance().mapToDtoList(categories, lang)));
	}

}
