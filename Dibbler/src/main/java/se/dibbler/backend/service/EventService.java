/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dao.EventDao;
import se.dibbler.backend.dao.PublishedEventDao;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.dto.EventDto;
import se.dibbler.backend.dto.create.PublishEventCreateDto;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakim
 */
@Stateless
@Path("/events")
public class EventService extends BaseWs<EventDto, Event, EventDao> {

    @EJB
    EventDao eventDao;

    @EJB
    PublishedEventDao publishEvent;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public EventDao getDao() {
        return eventDao;
    }

    /**
     * @summary Creates a new event that can be published later
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse create(EventDto data) {
        try {
            return super.insert(data);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType se.dibbler.backend.dto.full.EventDtoFull
     * @summary Gets a Event by id
     */
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getById(@PathParam("id") String id) {
        try {
            return getDao().getEventById(id).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Gets published events by location
     */
    @GET
    @Path("/byLocation")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getEventsByLocation(
            @QueryParam("longitude") Double longitude,
            @QueryParam("latitude") Double latitude,
            @QueryParam("radius") Double radius,
            @QueryParam("language") String languageId) {
        try {
            return publishEvent.getEventsByLocation(longitude, latitude, radius, Unit.KM).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Archives a Event
     */
    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse delete(@PathParam("id") Long id) {
        try {
            return super.delete(id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @inputType se.dibbler.backend.dto.EventDto
     * @summary Updates a Event
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse update(EventDto data, @PathParam("id") String id) {
        try {
            return eventDao.update(data, id).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.EventDto>
     * @summary Gets all events
     */
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getAll() {
        try {
            return super.getAll();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.full.EventDtoFull>
     * @summary Gets all events that belongs to a company
     */
    @GET
    @Path("/company/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getEventsByCompany(@PathParam("companyId") String companyId) {
        try {
            return eventDao.getEventByCompany(companyId).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @inputType se.dibbler.backend.dto.create.PublishEventCreateDto
     * @summary Publishes an event to public
     */
    @POST
    @Path("/publish")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse publishEvent(PublishEventCreateDto data) {
        try {
            return publishEvent.publishEvent(data).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

}
