/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper.summary;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.constants.MapperError;
import se.geomarket.backend.geomarket.dto.PointDto;
import se.geomarket.backend.geomarket.dto.summary.CompanySummaryDto;
import se.geomarket.backend.geomarket.dto.summary.EventSummaryDto;
import se.geomarket.backend.geomarket.dto.summary.EventTextSummaryDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.MethodResponse;
import se.geomarket.backend.geomarket.mapper.EventMapper;
import se.geomarket.backend.geomarket.mapper.PointMapper;

/**
 *
 * @author Joakim
 */
public class EventSummaryMapper extends BaseMapper<EventSummaryDto, Company> {

    private static final EventSummaryMapper INSTANCE = new EventSummaryMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSummaryMapper.class);

    public static EventSummaryMapper getInstance() {
        return INSTANCE;
    }

    public EventSummaryMapper() {
        super(EventSummaryDto.class, Company.class);
    }

    public MethodResponse<List<EventSummaryDto>> extractEvents(Company entity, String languageId) {
        try {
            List<EventSummaryDto> dtoList = new ArrayList<>();
            for (Event event : entity.getEvents()) {
                MethodResponse<EventSummaryDto> eventSummary = getEvent(event, entity, languageId);
                if (eventSummary.hasNoErrors) {
                    dtoList.add(eventSummary.getData());
                }
            }
            return MethodResponse.success(dtoList);
        } catch (Exception e) {
            LOGGER.debug("[ Error when mapping EventSummaryLIST for company with id: {} ] [ ERROR: {} ]", entity.getExtId());
            return MethodResponse.error(MapperError.EVENT_EXTRACT_EVENTS);
        }
    }

    private MethodResponse<EventSummaryDto> getEvent(Event event, Company entity, String languageId) {

        try {
            MethodResponse<Boolean> checkDateIsOk = checkDateIsOk(event);
            if (checkDateIsOk.hasErrors) {
                return MethodResponse.error(checkDateIsOk.getErrorCode());
            }

            if (checkDateIsOk.getData()) {
                EventSummaryDto summary = new EventSummaryDto();
                summary.setCategoryId(event.getCategory().getExtId());
                summary.setEventTypeId(event.getEventType().getExtId());
                summary.setExpires(event.getEndDate());
                summary.setId(event.getExtId());

                MethodResponse<CompanySummaryDto> companySummary = CompanySummaryMapper.getInstance().mapFromEntityToDto(entity);
                if (companySummary.hasErrors) {
                    return MethodResponse.error(companySummary.getErrorCode());
                }

                MethodResponse<PointDto> location = PointMapper.getInstance().mapFromEntityToDto(entity.getLocation());
                if (location.hasErrors) {
                    return MethodResponse.error(location.getErrorCode());
                }

                MethodResponse<EventTextSummaryDto> eventText = EventMapper.getInstance().getEventText(event, languageId);
                if (eventText.hasErrors) {
                    return MethodResponse.error(eventText.getErrorCode());
                }

                summary.setCompany(companySummary.getData());
                summary.setLocation(location.getData());
                summary.setEventText(eventText.getData());
                return MethodResponse.success(summary);
            } else {
                return MethodResponse.error(checkDateIsOk.getErrorCode());
            }
        } catch (Exception e) {
            LOGGER.debug("[ Error when mapping EventSummary event with id: {} ] [ ERROR: {} ]", event.getExtId());
            return MethodResponse.error(MapperError.EVENT_SUMMARY_GET_EVENT);
        }

    }

    private MethodResponse<Boolean> checkDateIsOk(Event event) {
        try {
            if (event.getEndDate().getTime() > DateTime.now().getMillis()) {
                if (event.getStartDate().getTime() < DateTime.now().getMillis()) {
                    MethodResponse.success(Boolean.TRUE);
                }
            }
            return MethodResponse.success(Boolean.FALSE);
        } catch (Exception e) {
            LOGGER.debug("[ Error when checking date for event with id: {} ] [ ERROR: {} ]", event.getExtId(), e);
            return MethodResponse.error(DaoError.EVENT_CHECK_DATE_OK);
        }
    }

}
