package controllers.v1;

import java.util.List;

import models.Event;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class EventService extends Controller {

	public static Result getEvents(int lat, int lon) {
		List<Event> events = Event.find.all();
		return (events.size() == 0 || events == null) ? ok("code 123") : ok(Json.toJson(events));
	}

}
