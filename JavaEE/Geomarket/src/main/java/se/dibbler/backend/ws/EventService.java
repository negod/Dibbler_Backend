/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.ws;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
import se.dibbler.backend.dao.EventDao;
import se.dibbler.backend.dto.EventDto;
import se.dibbler.backend.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.dto.summary.EventSummaryDto;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.WsResponse;
import se.dibbler.backend.mapper.EventMapper;

/**
 *
 * @author Joakim
 */
@Stateless
@Path("/events")
@Api(value = "/events", description = "Handles all Events")
public class EventService extends BaseWs<EventDto, Event, EventDao> {

    @EJB
    EventDao eventDao;

    @Override
    public EventDao getDao() {
        return eventDao;
    }

    @Override
    public BaseMapper<EventDto, Event> getMapper() {
        return EventMapper.getInstance();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addEventText")
    @ApiOperation(httpMethod = "POST", value = "Adds a new language to the eventText", response = String.class, nickname = "addEventText")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the updated Event"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse addEventText(
            @ApiParam(value = "The eventText to be inserted", required = true) LanguageTextDto data,
            @ApiParam(value = "The event to be updated", required = true) @QueryParam("eventId") String eventId) {
        return eventDao.addEventText(data, eventId).getWsResponse();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new Event", response = String.class, nickname = "create")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created Event", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class)
    })
    public WsResponse create(
            @ApiParam(value = "The event to be inserted", required = true) EventDto data,
            @ApiParam(value = "Id of the company that publishes the event", required = true) @QueryParam("companyId") String companyId,
            @ApiParam(value = "Id of the events category", required = true) @QueryParam("categoryId") String categoryId,
            @ApiParam(value = "Id of the events type", required = true) @QueryParam("eventTypeId") String eventTypeId,
            @ApiParam(value = "The default language of the event", required = true) @QueryParam("languageId") String languageId) {
        data.setCategoryId(categoryId);
        data.setCompanyId(companyId);
        data.setEventTypeId(eventTypeId);
        data.setLanguageId(languageId);
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets an Event by Id", response = EventDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Event", response = EventDto.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("/byLocation")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets all events based on the users position", response = EventSummaryDto.class, nickname = "getEventsByLocation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a list of events in the requested radius"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getEventsByLocation(
            @ApiParam(value = "The present longitude", required = true)
            @QueryParam("longitude") Double longitude,
            @ApiParam(value = "The present latitude", required = true)
            @QueryParam("latitude") Double latitude,
            @ApiParam(value = "How large area of Events that should return ( In KM )", required = true)
            @QueryParam("radius") Double radius,
            @ApiParam(value = "The default language the events will be presented in", required = true)
            @QueryParam("language") String languageId) {
        return eventDao.getEventsByLocation(longitude, latitude, radius, languageId).getWsResponse();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Delete an Event", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "event deleted"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Update an event", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated Event"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(EventDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets all events", response = EventDto.class, nickname = "get All")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns all the events in all languages"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getAll() {
        return super.getAll();
    }

}
