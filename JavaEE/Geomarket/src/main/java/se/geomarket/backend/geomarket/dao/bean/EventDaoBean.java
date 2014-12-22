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
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.constants.TextType;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dao.EventDao;
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.EventDto;
import se.geomarket.backend.geomarket.dto.languagesupport.LanguageTextDto;
import se.geomarket.backend.geomarket.dto.summary.EventSummaryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.entity.EventText;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.entity.LanguageText;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;
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
    public Response<String> addEventText(LanguageTextDto text, String eventId) {

        Response<Event> event = this.getByExtId(eventId);
        if (event.hasErrors) {
            return Response.error(event.getError());
        }

        Response<Language> language = languageDao.getByExtId(text.getLanguage());
        if (language.hasErrors) {
            return Response.error(language.getError());
        }

        try {
            EventText eventText = new EventText();
            eventText.setLanguage(language.getData());
            eventText.setEvent(event.getData());
            eventText.setTextType(TextType.NAME);
            event.getData().getEventTexts().add(eventText);
            return Response.success(event.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ Error when adding EventText ] [ ERROR ]", e);
            return Response.error(DaoError.EVENT_ADD_EVENT_TEXT);
        }
    }

    @Override
    public Response<List<EventSummaryDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, String languageId) {
        Response<List<Company>> locations = companyDao.getCompanyByLocation(longitude, latitude, radius, Unit.KM);
        if (locations.hasErrors) {
            return Response.error(locations.getError());
        }

        try {
            List<EventSummaryDto> allEvents = new ArrayList<>();
            for (Company company : locations.getData()) {
                Response<List<EventSummaryDto>> events = EventSummaryMapper.getInstance().extractEvents(company, languageId);
                if (events.hasNoErrors) {
                    allEvents.addAll(events.getData());
                }
            }
            return Response.success(allEvents);
        } catch (Exception e) {
            getLogger().error("[ Error when getting events by location ] [ ERROR ]", e);
            return Response.error(DaoError.EVENT_GET_EVENT_BY_LOCATION);
        }

    }

    @Override
    public Response<String> create(EventDto dto) {
        Event event = new Event();

        Response<Company> company = companyDao.getByExtId(dto.getCompanyId());
        if (company.hasErrors) {
            return Response.error(company.getError());
        }

        Response<EventType> eventType = eventTypeDao.getByExtId(dto.getEventTypeId());
        if (eventType.hasErrors) {
            return Response.error(eventType.getError());
        }

        Response<Category> category = categoryDao.getByExtId(dto.getCategoryId());
        if (category.hasErrors) {
            return Response.error(category.getError());
        }

        Response<Language> language = languageDao.getByExtId(dto.getLanguageId());
        if (language.hasErrors) {
            return Response.error(language.getError());
        }

        try {
            EventText heading = new EventText();
            heading.setLanguage(language.getData());
            heading.setValue(dto.getEventHeader());
            heading.setTextType(TextType.HEADER);
            heading.setEvent(event);

            EventText body = new EventText();
            body.setLanguage(language.getData());
            body.setValue(dto.getEventTextBody());
            heading.setTextType(TextType.TEXT_BODY);
            body.setEvent(event);

            event.setCategory(category.getData());
            event.setCompany(company.getData());
            event.setEventType(eventType.getData());
            event.setEndDate(dto.getEndDate());
            event.setStartDate(dto.getStartDate());
            event.setMaxRedeem(dto.getMaxredeem());
            event.setDefaultLanguage(language.getData());

            List<EventText> eventsTexts = new ArrayList<>();
            eventsTexts.add(heading);
            eventsTexts.add(body);
            event.setEventTexts(eventsTexts);

            if (company.getData().getEvents() == null) {
                List<Event> events = new ArrayList<>();
                events.add(event);
                company.getData().getEvents().add(event);
            } else {
                company.getData().getEvents().add(event);
            }
        } catch (Exception e) {
            getLogger().error("[ Error when creating event ] [ ERROR ]", e);
            return Response.error(DaoError.EVENT_CREATE);
        }

        return super.create(event);
    }

}
