package controllers.v1;

import java.util.List;

import mapper.event.EventMapper;
import models.Event;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import constants.Language;
import dto.EventDto;
import dto.Point;

public class EventService extends Controller {

	public static Result getEvents(Double lat, Double lon, Integer radius, String language) {

		Logger.info("[GetEvents: lat =  " + lat + "  lon = " + lon	+ " language =  " + language + "]");
		Language lang;
		try {
			lang = Language.valueOf(language.toUpperCase());
		} catch (Exception e) {
			return Results.noContent();
		}

		List<Event> events = Event.find.all();

		if (!events.isEmpty()) {
			List<EventDto> eventDtos = EventMapper.getInstance().mapToDtoList(events, lang);

			eventDtos.get(0).setLocation(new Point(11.972394, 7.709976));
			eventDtos.get(1).setLocation(new Point(11.972394, 57.709976));
			eventDtos.get(2).setLocation(new Point(11.97087, 57.707601));

			return ok(Json.toJson(eventDtos));
		}

		return Results.noContent();
	}

	public static Result getEventById(String id, String language) {
		Logger.info("[GetEventById : id =  " + id + " language =  " + language	+ "]");

		Language lang = Language.valueOf(language.toUpperCase());
		Event event = Event.find.where().like("external_id", id).findUnique();

		if (event != null) {
			return ok(Json.toJson(event));
		}

		return Results.noContent();
	}
}
