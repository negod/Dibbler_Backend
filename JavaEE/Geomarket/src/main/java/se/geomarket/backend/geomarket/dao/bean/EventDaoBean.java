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
import se.geomarket.backend.geomarket.generics.DaoResponse;
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
    public String addEventText(EventTextDto eventText, String eventId) {

        EventText eventTextEntity = EventTextMapper.getInstance().mapFromDtoToEntity(eventText);
        Event event = (Event) this.getByExtId(eventId);
        Language language = (Language) languageDao.getByExtId(eventText.getLanguageId());

        eventTextEntity.setLanguage(language);
        eventTextEntity.setEvent(event);

        event.getEventText().add(eventTextEntity);
        return event.getExtId();
    }

    @Override
    public List<EventSummaryDto> getEventsByLocation(Double longitude, Double latitude, Double radius, String languageId) {
        List<Company> locations = companyDao.getCompanyByLocation(longitude, latitude, radius, Unit.KM);
        List<EventSummaryDto> allEvents = new ArrayList<>();
        for (Company company : locations) {
            allEvents.addAll(EventSummaryMapper.getInstance().extractEvents(company, languageId));
        }
        return allEvents;
    }

    @Override
    public DaoResponse create(EventDto dto) {
        Event event = new Event();

        Company company = (Company) companyDao.getByExtId(dto.getCompanyId());
        EventType eventType = (EventType) eventTypeDao.getByExtId(dto.getEventTypeId());
        Category category = (Category) categoryDao.getByExtId(dto.getCategoryId());
        Language language = (Language) languageDao.getByExtId(dto.getLanguageId());

        EventText eventText = new EventText();
        eventText.setLanguage(language);
        eventText.setHeading(dto.getEventHeader());
        eventText.setBody(dto.getEventTextBody());
        eventText.setEvent(event);

        event.setCategory(category);
        event.setCompany(company);
        event.setEventType(eventType);
        event.setEndDate(dto.getEndDate());
        event.setStartDate(dto.getStartDate());
        event.setMaxRedeem(dto.getMaxredeem());
        event.setDefaultEventHeader(eventText.getHeading());
        event.setDefaultEventText(eventText.getBody());

        if (event.getEventText() == null) {
            List<EventText> eventsTexts = new ArrayList<>();
            eventsTexts.add(eventText);
            event.setEventText(eventsTexts);
        } else {
            event.getEventText().add(eventText);
        }

        if (company.getEvents() == null) {
            List<Event> events = new ArrayList<>();
            events.add(event);
            company.getEvents().add(event);
        } else {
            company.getEvents().add(event);
        }

        return super.create(event);
    }

}
