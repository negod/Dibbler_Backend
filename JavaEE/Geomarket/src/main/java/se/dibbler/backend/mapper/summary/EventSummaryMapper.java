/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper.summary;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.error.MapperError;
import se.dibbler.backend.dto.summary.CompanySummaryDto;
import se.dibbler.backend.dto.summary.EventSummaryDto;
import se.dibbler.backend.dto.summary.EventTextSummaryDto;
import se.dibbler.backend.dto.summary.LocationSummaryDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.EventTextMapper;
import se.dibbler.backend.mapper.PointMapper;

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

    public Response<List<EventSummaryDto>> extractEvents(Company entity, String languageId) {
        try {
            List<EventSummaryDto> dtoList = new ArrayList<>();
            for (Event event : entity.getEvents()) {
                Response<EventSummaryDto> eventSummary = getEvent(event, entity, languageId);
                if (eventSummary.hasNoErrors) {
                    dtoList.add(eventSummary.getData());
                }
            }
            return Response.success(dtoList);
        } catch (Exception e) {
            LOGGER.debug("[ Error when mapping EventSummaryLIST for company with id: {} ] [ ERROR: {} ]", entity.getExtId());
            return Response.error(MapperError.EVENT_EXTRACT_EVENTS);
        }
    }

    private Response<EventSummaryDto> getEvent(Event event, Company entity, String languageId) {

        try {
            Response<Boolean> checkDateIsOk = checkDateIsOk(event);
            if (checkDateIsOk.hasErrors) {
                return Response.error(checkDateIsOk.getError());
            }

            if (checkDateIsOk.getData()) {
                EventSummaryDto summary = new EventSummaryDto();
                summary.setCategoryId(event.getCategory().getExtId());
                summary.setEventTypeId(event.getEventType().getExtId());
                summary.setExpires(event.getEndDate());
                summary.setId(event.getExtId());

                Response<CompanySummaryDto> companySummary = CompanySummaryMapper.getInstance().mapFromEntityToDto(entity);
                if (companySummary.hasErrors) {
                    return Response.error(companySummary.getError());
                }

                Response<LocationSummaryDto> location = getLocationSummary(entity.getLocation());
                if (location.hasErrors) {
                    return Response.error(location.getError());
                }

                Response<EventTextSummaryDto> eventText = EventTextMapper.getInstance().getEventTextSummary(event, languageId);
                if (eventText.hasErrors) {
                    return Response.error(eventText.getError());
                }

                summary.setCompany(companySummary.getData());
                summary.setLocation(location.getData());
                summary.setEventText(eventText.getData());
                return Response.success(summary);
            } else {
                return Response.error(checkDateIsOk.getError());
            }
        } catch (Exception e) {
            LOGGER.debug("[ Error when mapping EventSummary event with id: {} ] [ ERROR: {} ]", event.getExtId());
            return Response.error(MapperError.EVENT_SUMMARY_GET_EVENT);
        }

    }

    private Response<LocationSummaryDto> getLocationSummary(Location location) {
        try {
            LocationSummaryDto dto = new LocationSummaryDto();
            dto.setLatitude(location.getLatitude());
            dto.setLongitude(location.getLongitude());
            return Response.success(dto);
        } catch (Exception e) {
            LOGGER.debug("[ Error when mapping Location to LocationDto: {} ] [ ERROR: {} ]", location.getExtId(), e);
            return Response.error(DaoError.EVENT_CHECK_DATE_OK);
        }
    }

    private Response<Boolean> checkDateIsOk(Event event) {
        try {
            if (event.getEndDate().getTime() > DateTime.now().getMillis()) {
                if (event.getStartDate().getTime() < DateTime.now().getMillis()) {
                    return Response.success(Boolean.TRUE);
                }
            }
            return Response.success(Boolean.FALSE);
        } catch (Exception e) {
            LOGGER.debug("[ Error when checking date for event with id: {} ] [ ERROR: {} ]", event.getExtId(), e);
            return Response.error(DaoError.EVENT_CHECK_DATE_OK);
        }
    }

}
