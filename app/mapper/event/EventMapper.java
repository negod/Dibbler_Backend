package mapper.event;

import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Event;
import models.EventType;
import constants.Language;
import dto.EventDto;

public class EventMapper {

	private static final EventMapper instance = new EventMapper();

	private EventMapper() {

	}

	public static EventMapper getInstance() {
		return instance;
	}

	public List<EventDto> mapList(List<Event> entityList, Language lang) {
		List<EventDto> dtoList = new ArrayList<EventDto>();
		for (Event event : entityList) {
			dtoList.add(entityToDto(event, lang));
		}
		return dtoList;
	}

	public EventDto entityToDto(Event event, Language lang) {
		EventDto dto = new EventDto();
		dto.setCategory(getCorrectCategoryText(event.getCategory(), lang));
		dto.setCompanyName(event.getCompany().getCompanyName());
		dto.setExpires(event.getEnddate().getTime());
		dto.setEventType(getCorrectEventTypeText(event.getEventType(), lang));
		dto.setId(event.getExternalId());
		return dto;
	}

	private String getCorrectEventTypeText(EventType eventType, Language lang) {
		switch (lang) {
		case EN:
			return eventType.getNameEng();
		case SE:
			return eventType.getNameSwe();
		default:
			return eventType.getDescriptionEng();
		}

	}

	private String getCorrectCategoryText(Category cat, Language lang) {

		switch (lang) {
		case EN:
			return cat.getNameEnglish();
		case SE:
			return cat.getNameSwedish();
		default:
			return cat.getNameEnglish();
		}

	}
}
