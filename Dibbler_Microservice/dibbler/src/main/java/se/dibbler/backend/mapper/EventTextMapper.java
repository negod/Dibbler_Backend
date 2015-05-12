/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import java.util.ArrayList;
import java.util.List;
import se.dibbler.backend.dto.languagesupport.EventTextDto;
import se.dibbler.backend.dto.summary.EventTextSummaryDto;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.error.MapperError;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventTextMapper extends BaseMapper<EventTextDto, EventText> {

    private static final EventTextMapper INSTANCE = new EventTextMapper();

    public static EventTextMapper getInstance() {
        return INSTANCE;
    }

    public EventTextMapper() {
        super(EventTextDto.class, EventText.class);
    }

    public Response<EventTextSummaryDto> getEventTextSummary(Event event, String languageId) {
        try {
            EventTextSummaryDto dto = new EventTextSummaryDto();

            Response<List<EventText>> eventTexts = getEventTextsInLanguage(event, languageId);
            if (eventTexts.hasErrors) {

                eventTexts = getEventTextsInLanguage(event, event.getDefaultLanguage().getExtId());
                if (eventTexts.hasErrors) {
                    return Response.error(eventTexts.getError());
                }

            }

            for (EventText text : event.getEventTexts()) {
                if (text.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                    switch (text.getTextType()) {
                        case HEADER:
                            dto.setHeading(text.getValue());
                            break;
                        case TEXT_BODY:
                            dto.setBody(text.getValue());
                    }
                }
            }

            return Response.success(dto);

        } catch (Exception e) {
            return Response.error(MapperError.EVENT_SUMMARY_GET_EVENTTEXT);
        }
    }

    public Response<List<EventText>> getEventTextsInLanguage(Event event, String languageId) {
        try {
            List<EventText> texts = new ArrayList<>();
            for (EventText text : event.getEventTexts()) {
                if (text.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                    texts.add(text);
                }
            }

            if (texts.isEmpty()) {
                return Response.error(MapperError.EVENT_NO_TEXT_FOR_LANGUAGE);
            }

            return Response.success(texts);
        } catch (Exception e) {
            return Response.error(MapperError.EVENT_SUMMARY_GET_EVENTTEXT);
        }

    }

}
