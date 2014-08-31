package controllers;

import java.util.List;

import models.Category;
import models.Company;
import models.CompanyUser;
import models.Event;
import models.EventText;
import models.EventType;
import models.Filter;
import models.FollowerEvent;
import models.FollowerReq;
import models.Movement;
import models.Role;
import models.Session;
import models.Setting;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	
	public static Result index() {
		return ok("");
	}

	public static Result getAllCategories() {
		List<Category> categories = Category.find.all();
		return ok(Json.toJson(categories));
	}

	public static Result getAllCompanies() {
		List<Company> copmanies = Company.find.all();
		return ok(Json.toJson(copmanies));
	}

	public static Result getAllCompanyUsers() {
		List<CompanyUser> companyUsers = CompanyUser.find.all();
		return ok(Json.toJson(companyUsers));
	}

	public static Result getAllEvents() {
		List<Event> events = Event.find.all();
		return ok(Json.toJson(events));
	}

	public static Result getAllEventTexts() {
		List<EventText> eventTexts = EventText.find.all();
		return ok(Json.toJson(eventTexts));
	}

	public static Result getAllEventTypes() {
		List<EventType> eventTypes = EventType.find.all();
		return ok(Json.toJson(eventTypes));
	}

	public static Result getAllFilters() {
		List<Filter> filters = Filter.find.all();
		return ok(Json.toJson(filters));
	}

	public static Result getAllFollowerEvents() {
		List<FollowerEvent> followerEvents = FollowerEvent.find.all();
		return ok(Json.toJson(followerEvents));
	}

	public static Result getAllFollowerReq() {
		List<FollowerReq> followerReqs = FollowerReq.find.all();
		return ok(Json.toJson(followerReqs));
	}

	public static Result getAllMovements() {
		List<Movement> movements = Movement.find.all();
		return ok(Json.toJson(movements));
	}

	public static Result getAllRoles() {
		List<Role> roles = Role.find.all();
		return ok(Json.toJson(roles));
	}

	public static Result getAllSessions() {
		List<Session> sessions = Session.find.all();
		return ok(Json.toJson(sessions));
	}
	
	public static Result getAllSettings() {
		List<Setting> settings = Setting.find.all();
		return ok(Json.toJson(settings));
	}

	public static Result getAllUsers() {
		List<User> users = User.find.all();
		return ok(Json.toJson(users));
	}


}
