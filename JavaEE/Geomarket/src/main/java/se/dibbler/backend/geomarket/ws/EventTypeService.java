/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.ws;

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
import se.dibbler.backend.geomarket.dao.EventTypeDao;
import se.dibbler.backend.geomarket.dao.EventTypeTextDao;
import se.dibbler.backend.geomarket.dto.EventTypeDto;
import se.dibbler.backend.geomarket.dto.languagesupport.EventTypeTextDto;
import se.dibbler.backend.geomarket.entity.EventType;
import se.dibbler.backend.geomarket.generics.BaseMapper;
import se.dibbler.backend.geomarket.generics.BaseWs;
import se.dibbler.backend.geomarket.generics.WsResponse;
import se.dibbler.backend.geomarket.mapper.EventTypeMapper;

/**
 *
 * @author Joakim
 */
@Stateless
@Path("/eventTypes")
@Api(value = "/eventTypes", description = "Handles all EventTypes")
public class EventTypeService extends BaseWs<EventTypeDto, EventType, EventTypeDao> {

    @EJB
    EventTypeDao eventTypeDao;

    @EJB
    EventTypeTextDao eventTypeText;

    @Override
    public EventTypeDao getDao() {
        return eventTypeDao;
    }

    @Override
    public BaseMapper<EventTypeDto, EventType> getMapper() {
        return EventTypeMapper.getInstance();
    }

    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a eventtype by Id", response = EventTypeDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a EventType and all of its added languages", response = EventTypeDto.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse getById(@ApiParam(value = "The id of the EventType", required = true) @PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("/language/{languageId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets all EventTypes in a certain language", response = EventTypeTextDto.class, nickname = "getAllByLanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a list of EventTypeNames but only in the requested language", response = EventTypeTextDto.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse getAllByLanguage(@ApiParam(value = "The id of the language", required = true) @PathParam("languageId") String languageId) {
        return eventTypeDao.getEventTypesByLanguage(languageId).getWsResponse();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all eventtypes", response = EventTypeDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a all EventTypes and all of it´s added languages", response = EventTypeDto.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse getAll() {
        return super.getAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Adds a new EventType and also creates a new LanguageText in the default language", response = String.class, nickname = "create")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the created EventType", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse insert(
            @ApiParam(value = "The description of the EventType", required = true) @QueryParam("description") String description,
            @ApiParam(value = "The default name of the eventtype", required = true) @QueryParam("defaultName") String defaultName,
            @ApiParam(value = "The id of the default language", required = true) @QueryParam("languageId") String languageId) {
        return super.insert(new EventTypeDto(languageId, defaultName, description));
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addlanguage")
    @ApiOperation(httpMethod = "POST", value = "Adds a description to the eventtype in the specified language", response = String.class, nickname = "addlanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the created EventTypeText ( This is not the same as the EventTypes id!", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse addLanguage(
            @ApiParam(value = "The id for the eventtype that the new language will be added to", required = true) @QueryParam("eventTypeId") String categoryId,
            @ApiParam(value = "The name of the eventtype in the requested language", required = true) @QueryParam("name") String name,
            @ApiParam(value = "The id of the language to add", required = true) @QueryParam("languageId") String language) {
        return eventTypeDao.addLanguage(categoryId, name, language).getWsResponse();
    }

    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes an eventtype by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1004, message = "Error when deleting from database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse delete(@ApiParam(value = "The id for the EventType to delete", required = true) @PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates an eventtype", response = String.class, nickname = "update", notes = "This Method is not supported yet")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated EventType", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse update(
            @ApiParam(value = "The new descripton of the EventType", required = true) @QueryParam("description") String description,
            @ApiParam(value = "The id of the EventType", required = true) @PathParam("id") String id) {
        return eventTypeDao.updateEventTypeDescription(description, id).getWsResponse();
    }

    @DELETE
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "DELETE", value = "Deletes an EventTypeName language by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1004, message = "Error when deleting from database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse deleteLanguage(@ApiParam(value = "The id of the language for the EventTypeName", required = true) @PathParam("id") Long id) {
        return eventTypeText.delete(id).getWsResponse();
    }

    @PUT
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates an events language type", response = String.class, nickname = "update", notes = "This Method is not supported yet")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated EventType", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse updateLanguage(
            @ApiParam(value = "The id of the EventTypeName", required = true) @PathParam("id") String id,
            @ApiParam(value = "The id of the EventTypeName", required = true) @QueryParam("name") String name) {
        return eventTypeText.updateEventTypeNameByEventTextId(name, id).getWsResponse();
    }

}
