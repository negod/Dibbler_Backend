/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.search.query.dsl.Unit;
import static scala.io.BytePickle.data;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dao.EventDao;
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.EventDto;
import se.geomarket.backend.geomarket.dto.EventTextDto;
import se.geomarket.backend.geomarket.dto.summary.EventSummaryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.entity.EventText;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;
import se.geomarket.backend.geomarket.mapper.EventTextMapper;
import se.geomarket.backend.geomarket.mapper.summary.EventSummaryMapper;

/**
 *
 * @author Joakim
 */
@Stateless
public class EventDaoBean extends BaseDaoBean<Event, EventDto> implements EventDao<Event, EventDto> {

    @EJB
    EventTypeDao eventTypeDao;
    @EJB
    CategoryDao categoryDao;
    @EJB
    LanguageDao languageDao;
    @EJB
    CompanyDao companyDao;

    public EventDaoBean() {
        super(Event.class);
    }

    @Override
    public MethodResponse<String> addEventText(EventTextDto eventText, String eventId) {

        MethodResponse<EventText> eventTextEntity = EventTextMapper.getInstance().mapFromDtoToEntity(eventText);
        if (eventTextEntity.hasErrors) {
            return MethodResponse.error(eventTextEntity.getErrorCode());
        }

        MethodResponse<Event> event = this.getByExtId(eventId);
        if (event.hasErrors) {
            return MethodResponse.error(event.getErrorCode());
        }

        MethodResponse<Language> language = languageDao.getByExtId(eventText.getLanguageId());
        if (language.hasErrors) {
            return MethodResponse.error(language.getErrorCode());
        }

        try {
            eventTextEntity.getData().setLanguage(language.getData());
            eventTextEntity.getData().setEvent(event.getData());
            event.getData().getEventText().add(eventTextEntity.getData());
            return MethodResponse.success(event.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ Error when adding EventText ] [ ERROR ]", e);
            return MethodResponse.error(DaoError.EVENT_ADD_EVENT_TEXT);
        }
    }

    @Override
    public MethodResponse<List<EventSummaryDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, String languageId) {
        MethodResponse<List<Company>> locations = companyDao.getCompanyByLocation(longitude, latitude, radius, Unit.KM);
        if (locations.hasErrors) {
            return MethodResponse.error(locations.getErrorCode());
        }

        try {
            List<EventSummaryDto> allEvents = new ArrayList<>();
            for (Company company : locations.getData()) {
                MethodResponse<List<EventSummaryDto>> events = EventSummaryMapper.getInstance().extractEvents(company, languageId);
                if (events.hasNoErrors) {
                    allEvents.addAll(events.getData());
                }
            }
            return MethodResponse.success(allEvents);
        } catch (Exception e) {
            getLogger().error("[ Error when getting events by location ] [ ERROR ]", e);
            return MethodResponse.error(DaoError.EVENT_GET_EVENT_BY_LOCATION);
        }

    }

    @Override
    public MethodResponse<String> create(EventDto dto) {
        Event event = new Event();

        MethodResponse<Company> company = companyDao.getByExtId(dto.getCompanyId());
        if (company.hasErrors) {
            return MethodResponse.error(company.getErrorCode());
        }

        MethodResponse<EventType> eventType = eventTypeDao.getByExtId(dto.getEventTypeId());
        if (eventType.hasErrors) {
            return MethodResponse.error(eventType.getErrorCode());
        }

        MethodResponse<Category> category = categoryDao.getByExtId(dto.getCategoryId());
        if (category.hasErrors) {
            return MethodResponse.error(category.getErrorCode());
        }

        MethodResponse<Language> language = languageDao.getByExtId(dto.getLanguageId());
        if (language.hasErrors) {
            return MethodResponse.error(language.getErrorCode());
        }

        try {
            EventText eventText = new EventText();
            eventText.setLanguage(language.getData());
            eventText.setHeading(dto.getEventHeader());
            eventText.setBody(dto.getEventTextBody());
            eventText.setEvent(event);

            event.setCategory(category.getData());
            event.setCompany(company.getData());
            event.setEventType(eventType.getData());
            event.setEndDate(dto.getEndDate());
            event.setStartDate(dto.getStartDate());
            event.setMaxRedeem(dto.getMaxredeem());
            event.setDefaultEventHeader(eventText.getHeading());
            event.setDefaultEventText(eventText.getBody());

            List<EventText> eventsTexts = new ArrayList<>();
            eventsTexts.add(eventText);
            event.setEventText(eventsTexts);

            if (company.getData().getEvents() == null) {
                List<Event> events = new ArrayList<>();
                events.add(event);
                company.getData().getEvents().add(event);
            } else {
                company.getData().getEvents().add(event);
            }
        } catch (Exception e) {
            getLogger().error("[ Error when creating event ] [ ERROR ]", e);
            return MethodResponse.error(DaoError.EVENT_CREATE);
        }

        return super.create(event);
    }

}
