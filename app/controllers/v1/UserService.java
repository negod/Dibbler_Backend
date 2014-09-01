package controllers.v1;

import mapper.event.UserMapper;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

import dto.UserDto;

public class UserService extends Controller{
	
	public static Result insertUser() {
		
		UserDto userDto = Json.fromJson(request().body().asJson(), UserDto.class);
		Logger.info("[Inserting User : " + userDto.toString() + "]");
		User userEntity = UserMapper.getInstance().mapToEntity(userDto);
		Ebean.save(userEntity);
		return created(Json.toJson(userEntity));
		
	}

}
