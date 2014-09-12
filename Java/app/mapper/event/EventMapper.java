package mapper.event;

import mapper.GenericMapperLang;
import models.Category;
import models.Event;
import models.EventType;
import constants.Language;
import dto.EventDto;

public class EventMapper extends GenericMapperLang<Event, EventDto> {

	private static final EventMapper instance = new EventMapper();

	private EventMapper() {

	}

	public static EventMapper getInstance() {
		return instance;
	}

	@Override
	public EventDto mapToDto(Event entity, Language lang) {
		EventDto dto = new EventDto();
		dto.setCategory(getCorrectCategoryText(entity.getCategory(), lang));
		dto.setCompanyName(entity.getCompany().getCompanyName());
		dto.setExpires(entity.getEnddate().getTime());
		dto.setEventType(getCorrectEventTypeText(entity.getEventType(), lang));
		dto.setId(entity.getExternalId());
		return dto;
	}

	@Override
	public Event mapToEntity(EventDto dto) {
		// TODO Auto-generated method stub
		return null;
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
