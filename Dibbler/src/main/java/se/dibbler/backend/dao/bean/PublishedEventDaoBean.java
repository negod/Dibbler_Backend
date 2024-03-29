/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.dao.CategoryDao;
import se.dibbler.backend.dao.EventDao;
import se.dibbler.backend.dao.EventTextDao;
import se.dibbler.backend.dao.EventTypeDao;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dao.PublishedEventDao;
import se.dibbler.backend.dto.PublishedEventDto;
import se.dibbler.backend.dto.create.PublishEventCreateDto;
import se.dibbler.backend.dto.summary.PublishedEventSummaryDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.entity.EventType;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.entity.PublishedEvent;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Mapper;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.EventTextMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class PublishedEventDaoBean extends BaseDaoBean<PublishedEvent, PublishedEventDto> implements PublishedEventDao<PublishedEvent, PublishedEventDto> {

    @EJB
    EventDao eventDao;

    @EJB
    LanguageDao languageDao;

    @EJB
    EventTextDao eventTextDao;

    @EJB
    CategoryDao categorytDao;

    @EJB
    EventTypeDao eventTypeDao;

    public PublishedEventDaoBean() {
        super(PublishedEvent.class, PublishedEventDto.class);
    }

    @Override
    public Response<List<PublishedEventDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, Unit unit) {
        try {
            Session session = (Session) super.getEntityManager().getDelegate();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(PublishedEvent.class).get();

            Date nowDate = new Date();
            Query q2 = builder.range().onField("starts").below(nowDate).excludeLimit().createQuery();
            Query q3 = builder.range().onField("expires").above(nowDate).excludeLimit().createQuery();
            Query query = builder.spatial().onDefaultCoordinates()
                    .within(radius, unit)
                    .ofLatitude(latitude)
                    .andLongitude(longitude)
                    .createQuery();

            BooleanQuery bq = new BooleanQuery();
            bq.add(q2, BooleanClause.Occur.MUST);
            bq.add(query, BooleanClause.Occur.MUST);
            bq.add(q3, BooleanClause.Occur.MUST);

            org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, PublishedEvent.class);

            System.out.println(hibQuery.getQueryString());
            List<PublishedEvent> results = hibQuery.list();
            return super.mapToDtoList(results);
        } catch (Exception e) {
            super.getLogger().error("[ Error when getting events by location ] [LONG: {}] [LAT: {}] [RADIUS: {}] [ ERROR: {}]", longitude, latitude, radius, e.getMessage());
            return Response.error(DaoError.EVENT_BY_LOCATION);
        }
    }

    @Override
    public Response<String> create(PublishedEventDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response<String> publishEvent(PublishEventCreateDto dto) {
        try {

            Response<Event> eventEntity = eventDao.getByExtId(dto.getEventId());
            if (eventEntity.hasErrors) {
                return Response.error(eventEntity.getError());
            }

            Response<Language> languageEntity = languageDao.getByExtId(dto.getLanguageId());
            if (languageEntity.hasErrors) {
                return Response.error(languageEntity.getError());
            }

            Response<List<EventText>> eventTexts = EventTextMapper.getInstance().getEventTextsInLanguage(eventEntity.getData(), dto.getLanguageId());
            if (eventTexts.hasErrors) {
                return Response.error(eventTexts.getError());
            }

            Company company = eventEntity.getData().getCompany();

            Response<List<Location>> controlledLocations = checkCompanyHasLocations(company, dto.getLocations());
            if (controlledLocations.hasErrors) {
                return Response.error(controlledLocations.getError());
            }

            List<PublishedEvent> events = new ArrayList<>();
            for (Location location : controlledLocations.getData()) {

                PublishedEvent publishedEvent = Mapper.getInstance().getMapper().map(eventEntity.getData(), PublishedEvent.class);
                publishedEvent.setCompany(eventEntity.getData().getCompany());
                publishedEvent.setLanguage(languageEntity.getData());
                publishedEvent.setEvent(eventEntity.getData());

                publishedEvent.setId(null);
                publishedEvent.setExtId(null);

                publishedEvent.setStarts(dto.getStartDate());
                publishedEvent.setExpires(dto.getExpireDate());

                publishedEvent.setLatitude(location.getLatitude());
                publishedEvent.setLongitude(location.getLongitude());

                for (EventText text : eventTexts.getData()) {
                    if (text.getLanguage().getExtId().equalsIgnoreCase(dto.getLanguageId())) {
                        publishedEvent.setHeading(text.getHeader());
                        publishedEvent.setBody(text.getDescription());
                    }
                }
                events.add(publishedEvent);
            }

            return super.createBatch(events);

        } catch (Exception e) {
            super.getLogger().error("[ Error when publishing event ] [ERROR: {}] ", e.getMessage());
            return Response.error(DaoError.EVENT_PUBLISH);
        }
    }

    private Response<List<Location>> checkCompanyHasLocations(Company company, List<String> locationIds) {

        List<Location> locations = new ArrayList<>();
        Map<String, Location> uniqueLocations = new HashMap<>();
        for (Location companyLocation : company.getLocations()) {
            uniqueLocations.put(companyLocation.getExtId(), companyLocation);
        }

        for (String loc : locationIds) {
            if (!uniqueLocations.containsKey(loc)) {
                return Response.error(DaoError.EVENT_PUBLISH_COMPANY_HAS_NOT_LOCATION);
            } else {
                locations.add(uniqueLocations.get(loc));
            }
        }
        return Response.success(locations);

    }

    @Override
    public Response<List<PublishedEvent>> getPublishedEventsByExpiredDate(Date expired) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("expiryDate", expired);
            Response<List<PublishedEvent>> events = super.getListByNamedQuery(DibblerNamedQueries.PUBLISHED_EVENT_FINDBY_EXPIRED_DATE, params);

            if (events.hasErrors) {
                return Response.error(events.getError());
            }

            return events;
        } catch (Exception e) {
            super.getLogger().error("[ Error when getting published events by expiredDate ] [ERROR: {}] ", e.getMessage());
            return Response.error(DaoError.EVENT_PUBLISH);
        }

    }

    @Override
    public Response<String> update(PublishedEventDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response updatePublishedEvent(PublishedEventSummaryDto event) {
        try {
            Response<PublishedEvent> publishedEvent = super.getByExtId(event.getId());

            if (publishedEvent.hasErrors) {
                return Response.error(publishedEvent.getError());
            }

            if (event.getExpires() != null) {
                publishedEvent.getData().setExpires(new Date(event.getExpires()));
            }

            if (event.getStarts() != null) {
                publishedEvent.getData().setStarts(new Date(event.getStarts()));
            }

            if (event.getBody() != null) {
                publishedEvent.getData().setBody(event.getBody());
            }

            if (event.getHeading() != null) {
                publishedEvent.getData().setBody(event.getHeading());
            }

            if (event.getEventTypeId() != null) {
                Response<EventType> eventType = eventTypeDao.getByExtId(event.getEventTypeId());
                if (eventType.hasErrors) {
                    return Response.error(eventType.getError());
                }
                publishedEvent.getData().setEventTypeId(eventType.getData().getExtId());
            }

            Response<PublishedEventDto> dto = getMapper().mapFromEntityToDto(publishedEvent.getData());
            if (dto.hasErrors) {
                return Response.error(dto.getError());
            }

            return Response.success(publishedEvent.getData().getExtId());

        } catch (Exception e) {
            return Response.error(DaoError.EVENT_PUBLISH_UPDATE, e.getMessage());
        }
    }

    @Override
    public Response getPublishedEventByCompany(String companyId) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("companyId", companyId);
            Response<List<PublishedEvent>> publishedEvent = super.getListByNamedQuery(DibblerNamedQueries.PUBLISHED_EVENT_FINDBY_COMPANY, params);

            if (publishedEvent.hasErrors) {
                return Response.error(publishedEvent.getError());
            }

            Response<List<PublishedEventDto>> publishedEventMapped = getMapper().mapToDtoList(publishedEvent.getData());
            if (publishedEventMapped.hasErrors) {
                return Response.error(publishedEventMapped.getError());
            }

            return Response.success(publishedEventMapped);

        } catch (Exception e) {
            return Response.error(GenericError.READ, e.getMessage());
        }

    }

}
