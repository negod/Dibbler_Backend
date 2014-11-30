/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.constants.MapperError;
import se.geomarket.backend.geomarket.dto.EventDto;
import se.geomarket.backend.geomarket.dto.summary.EventTextSummaryDto;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.entity.EventText;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.MethodResponse;

/**
 *
 * @author Joakim
 */
public class EventMapper extends BaseMapper<EventDto, Event> {

    private static final EventMapper INSTANCE = new EventMapper();

    public static EventMapper getInstance() {
        return INSTANCE;
    }

    public EventMapper() {
        super(EventDto.class, Event.class);
    }

    public MethodResponse<EventTextSummaryDto> getEventText(Event entity, String languageId) {
        try {
            for (EventText eventText : entity.getEventText()) {
                if (eventText.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                    EventTextSummaryDto dto = new EventTextSummaryDto();
                    dto.setBody(eventText.getBody());
                    dto.setHeading(eventText.getHeading());
                    MethodResponse.success(dto);
                }
            }
            EventTextSummaryDto dto = new EventTextSummaryDto();
            dto.setBody(entity.getDefaultEventText());
            dto.setHeading(entity.getDefaultEventHeader());
            return MethodResponse.success(dto);
        } catch (Exception e) {
            return MethodResponse.error(MapperError.EVENT_SUMMARY_GET_EVENTTEXT);
        }
    }

}
