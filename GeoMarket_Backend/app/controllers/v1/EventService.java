package controllers.v1;

import java.util.List;

import mapper.EventMapper;
import models.Event;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import constants.Language;
import dto.EventDto;

public class EventService extends Controller {

	public static Result getEvents(int lat, int lon, String language) {

		Language lang = Language.valueOf(language);

		List<Event> events = Event.find.all();
		if (!events.isEmpty() || events != null) {
			List<EventDto> eventDtos = EventMapper.getInstance().mapList(
					events, lang);
			return ok(Json.toJson(eventDtos));
		}

		return Results.noContent();
	}

}
