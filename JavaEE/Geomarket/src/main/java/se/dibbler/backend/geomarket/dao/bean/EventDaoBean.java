/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.geomarket.error.DaoError;
import se.dibbler.backend.geomarket.constants.TextType;
import se.dibbler.backend.geomarket.dao.CategoryDao;
import se.dibbler.backend.geomarket.dao.CompanyDao;
import se.dibbler.backend.geomarket.dao.EventDao;
import se.dibbler.backend.geomarket.dao.EventTypeDao;
import se.dibbler.backend.geomarket.dao.LanguageDao;
import se.dibbler.backend.geomarket.dto.EventDto;
import se.dibbler.backend.geomarket.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.geomarket.dto.summary.EventSummaryDto;
import se.dibbler.backend.geomarket.entity.Category;
import se.dibbler.backend.geomarket.entity.Company;
import se.dibbler.backend.geomarket.entity.Event;
import se.dibbler.backend.geomarket.entity.EventText;
import se.dibbler.backend.geomarket.entity.EventType;
import se.dibbler.backend.geomarket.entity.Language;
import se.dibbler.backend.geomarket.entity.LanguageText;
import se.dibbler.backend.geomarket.generics.BaseDaoBean;
import se.dibbler.backend.geomarket.generics.Response;
import se.dibbler.backend.geomarket.mapper.summary.EventSummaryMapper;

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
            body.setTextType(TextType.TEXT_BODY);
            body.setEvent(event);

            event.setCategory(category.getData());
            event.setCompany(company.getData());
            event.setEventType(eventType.getData());
            event.setEndDate(dto.getEndDate());
            event.setStartDate(dto.getStartDate());
            event.setMaxRedeem(dto.getMaxRedeem());
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
