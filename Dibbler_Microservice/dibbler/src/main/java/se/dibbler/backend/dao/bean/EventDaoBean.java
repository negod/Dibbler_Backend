/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.constants.DibblerConstants;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.constants.TextType;
import se.dibbler.backend.dao.CategoryDao;
import se.dibbler.company.dao.CompanyDao;
import se.dibbler.backend.dao.EventDao;
import se.dibbler.backend.dao.EventTypeDao;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dao.PublishedEventDao;
import se.dibbler.backend.dto.EventDto;
import se.dibbler.backend.dto.create.PublishEventCreateDto;
import se.dibbler.backend.dto.full.EventDtoFull;
import se.dibbler.backend.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.dto.summary.EventSummaryDto;
import se.dibbler.backend.entity.Category;
import se.dibbler.company.entity.Company;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.entity.EventType;
import se.dibbler.backend.entity.Language;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDaoBean;
import se.dibbler.generic.dao.BaseMapper;
import se.dibbler.generic.error.GenericError;
import se.dibbler.generic.file.DibblerFileType;
import se.dibbler.generic.file.FileCreator;
import se.dibbler.generic.file.PictureUrl;
import se.dibbler.generic.mapper.Mapper;

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
    @EJB
    PublishedEventDao publishedEvent;

    BaseMapper<EventDtoFull, Event> fullMapper = new BaseMapper(EventDtoFull.class, Event.class);

    public EventDaoBean() {
        super(Event.class, EventDto.class);
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
            event.setRecipientType(dto.getRecipientType());

            List<EventText> eventsTexts = new ArrayList<>();
            eventsTexts.add(heading);
            eventsTexts.add(body);
            event.setEventTexts(eventsTexts);

            if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                Response<Map<PictureUrl, String>> createImage = FileCreator.createFilesFromBase64String(dto.getPicture(), DibblerConstants.IMAGE_URL, 80, 40, DibblerFileType.EVENT);
                if (createImage.hasNoErrors) {
                    event.setImageUrl("N/A");
                    event.setImageSmallUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_SMALl));
                    event.setImageLargeUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_LARGE));
                }
            } else {
                event.setImageUrl("N/A");
                event.setImageSmallUrl("N/A");
                event.setImageLargeUrl("N/A");
            }

            if (company.getData().getEvents() == null) {
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

    @Override
    public Response<String> publishEvent(PublishEventCreateDto data) {
        return publishedEvent.publishEvent(data);
    }

    @Override
    public Response<List<EventDtoFull>> getEventByCompany(String companyId) {
        try {

            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }

            List<EventDtoFull> events = new ArrayList<>();

            for (Event event : company.getData().getEvents()) {

                EventDtoFull eventDto = Mapper.getInstance().getMapper().map(event, EventDtoFull.class);
                if (eventDto != null) {
                    events.add(eventDto);
                }
            }

            if (events.isEmpty()) {
                return Response.error(DaoError.EVENT_NO_EVENTS_IN_COMPANY);
            } else {
                return Response.success(events);
            }

        } catch (Exception e) {
            getLogger().error("[ Error when creating event ] [ ERROR ]", e);
            return Response.error(DaoError.EVENT_GET_BY_COMPANY);
        }

    }

    @Override
    public Response<List<EventSummaryDto>> getPublishedEventsByLocation(Double longitude, Double latitude, Double radius, String languageId) {
        return publishedEvent.getEventsByLocation(longitude, latitude, radius, Unit.KM);
    }

    @Override
    public Response<String> update(EventDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<EventDtoFull> getEventById(String eventId) {
        Response<Event> event = super.getByExtId(eventId);
        if (event.hasNoErrors) {
            return fullMapper.mapFromEntityToDto(event.getData());
        } else {
            return Response.error(event.getError());
        }
    }

}