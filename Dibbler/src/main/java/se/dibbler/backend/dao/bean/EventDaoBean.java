/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.constants.DibblerConstants;
import se.dibbler.backend.constants.DibblerFileType;
import se.dibbler.backend.constants.PictureUrl;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.dao.CategoryDao;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dao.EventDao;
import se.dibbler.backend.dao.EventTypeDao;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dao.LocationDao;
import se.dibbler.backend.dao.PublishedEventDao;
import se.dibbler.backend.dto.EventDto;
import se.dibbler.backend.dto.EventTextDto;
import se.dibbler.backend.dto.create.PublishEventCreateDto;
import se.dibbler.backend.dto.full.EventDtoFull;
import se.dibbler.backend.dto.summary.EventSummaryDto;
import se.dibbler.backend.entity.Category;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.entity.EventType;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.DibblerImageUtil;
import se.dibbler.backend.generics.Mapper;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.utils.FileCreator;

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
    LocationDao locationDao;
    @EJB
    CompanyDao companyDao;
    @EJB
    PublishedEventDao publishedEvent;

    BaseMapper<EventDtoFull, Event> fullMapper = new BaseMapper(EventDtoFull.class, Event.class);

    public EventDaoBean() {
        super(Event.class, EventDto.class);
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

        try {
            event.setCategory(category.getData());
            event.setCompany(company.getData());
            event.setEventType(eventType.getData());
            event.setEndDate(dto.getEndDate());
            event.setStartDate(dto.getStartDate());
            event.setMaxRedeem(dto.getMaxRedeem());
            event.setRecipientType(dto.getRecipientType());
            event.setGenerateFollowUp(dto.getGenerateFollowUp());
            event.setPublishAtBranch(dto.getPublishAtBranch());
            event.setPublishAtCompany(dto.getPublishAtCompany());
            event.setPublishAtLocation(dto.getPublishAtLocation());
            event.setQrCode(UUID.randomUUID().toString());

            List<EventText> eventsTexts = new ArrayList<>();
            for (EventTextDto eventTextDto : dto.getEventTexts()) {
                Response<Language> textLanguage = languageDao.getByExtId(eventTextDto.getLanguageId());
                if (textLanguage.hasNoErrors) {
                    EventText text = new EventText();
                    text.setLanguage(textLanguage.getData());
                    text.setEvent(event);
                    text.setDescription(eventTextDto.getDescription());
                    text.setHeader(eventTextDto.getHeader());
                    eventsTexts.add(text);
                }
            }

            if (dto.getLocationIds() != null) {
                List<Location> locationList = new ArrayList<>();
                for (String locationId : dto.getLocationIds()) {
                    Response<Location> location = locationDao.getByExtId(locationId);
                    if (location.hasNoErrors) {
                        locationList.add(location.getData());
                    } else {
                        return Response.error(DaoError.EVENT_UPDATE_GET_LOCATION);
                    }
                }
                event.setLocations(locationList);
            }

            //TODO set correct default language
            event.setDefaultLanguage(eventsTexts.get(0).getLanguage());

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

        Response<Event> event = super.getByExtId(extId);
        if (event.hasErrors) {
            return Response.error(event.getError());
        }

        if (!dto.getCompanyId().equalsIgnoreCase(event.getData().getCompany().getExtId())) {
            Response<Company> company = companyDao.getByExtId(dto.getCompanyId());
            if (company.hasErrors) {
                return Response.error(company.getError());
            }
            event.getData().setCompany(company.getData());
        }

        if (!dto.getEventTypeId().equalsIgnoreCase(event.getData().getEventType().getExtId())) {
            Response<EventType> eventType = eventTypeDao.getByExtId(dto.getEventTypeId());
            if (eventType.hasErrors) {
                return Response.error(eventType.getError());
            }
            event.getData().setEventType(eventType.getData());
        }

        if (!dto.getCategoryId().equalsIgnoreCase(event.getData().getCategory().getExtId())) {
            Response<Category> category = categoryDao.getByExtId(dto.getCategoryId());
            if (category.hasErrors) {
                return Response.error(category.getError());
            }
            event.getData().setCategory(category.getData());
        }

        try {

            event.getData().setEndDate(dto.getEndDate());
            event.getData().setStartDate(dto.getStartDate());
            event.getData().setMaxRedeem(dto.getMaxRedeem());
            event.getData().setRecipientType(dto.getRecipientType());
            event.getData().setGenerateFollowUp(dto.getGenerateFollowUp());
            event.getData().setPublishAtBranch(dto.getPublishAtBranch());
            event.getData().setPublishAtCompany(dto.getPublishAtCompany());
            event.getData().setPublishAtLocation(dto.getPublishAtLocation());

            if (dto.getEventTexts() != null) {
                for (EventTextDto eventTextDto : dto.getEventTexts()) {

                    Response<EventText> text = getEventTextByLanguage(event.getData().getEventTexts(), eventTextDto.getLanguageId());
                    if (text.hasErrors) {
                        Response<Language> textLanguage = languageDao.getByExtId(eventTextDto.getLanguageId());
                        if (textLanguage.hasNoErrors) {
                            EventText eventText = new EventText();
                            eventText.setLanguage(textLanguage.getData());
                            eventText.setEvent(event.getData());
                            eventText.setDescription(eventTextDto.getDescription());
                            eventText.setHeader(eventTextDto.getHeader());
                            event.getData().getEventTexts().add(eventText);
                        }
                    } else {
                        text.getData().setDescription(eventTextDto.getDescription());
                        text.getData().setHeader(eventTextDto.getHeader());
                    }
                }
            }

            // Do check if the location belongs to the a company!
            if (dto.getLocationIds() != null) {
                List<Location> locationList = new ArrayList<>();
                for (String locationId : dto.getLocationIds()) {
                    Response<Location> location = locationDao.getByExtId(locationId);
                    if (location.hasNoErrors) {
                        locationList.add(location.getData());
                    } else {
                        return Response.error(DaoError.EVENT_UPDATE_GET_LOCATION);
                    }
                }

                event.getData().getLocations().clear();
                event.getData().setLocations(locationList);
            }

            if (dto.getPicture() != null) {
                if (DibblerImageUtil.isStringBase64(dto.getPicture())) {
                    if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                        Response<Map<PictureUrl, String>> createImage = FileCreator.createFilesFromBase64String(dto.getPicture(), DibblerConstants.IMAGE_URL, 80, 40, DibblerFileType.EVENT);
                        if (createImage.hasNoErrors) {
                            event.getData().setImageUrl("N/A");
                            event.getData().setImageSmallUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_SMALl));
                            event.getData().setImageLargeUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_LARGE));
                        }
                    } else {
                        event.getData().setImageUrl("N/A");
                        event.getData().setImageSmallUrl("N/A");
                        event.getData().setImageLargeUrl("N/A");
                    }
                } else {
                    getLogger().debug("[ File is not Base64! ]");
                }
            }

        } catch (Exception e) {
            getLogger().error("[ Error when creating event ] [ ERROR ]", e);
            return Response.error(DaoError.EVENT_UPDATE, e.getCause().toString());
        }

        return super.update(event.getData());
    }

    private Response<EventText> getEventTextByLanguage(List<EventText> eventText, String languageId) {
        for (EventText text : eventText) {
            if (text.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                return Response.success(text);
            }
        }
        return Response.error(DaoError.EVENT_UPDATE);
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
