/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

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
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.EventTypeTextDao;
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.generics.WsResponse;
import se.geomarket.backend.geomarket.mapper.EventTypeMapper;

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
        @ApiResponse(code = 200, message = "Returns an eventtype"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("/language/{languageId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets all EventTypes in a certain language", response = NameSummaryDto.class, nickname = "getAllByLanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns an eventtype in the requested language"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getAllByLanguage(@PathParam("languageId") String languageId) {
        return eventTypeDao.getEventTypesByLanguage(languageId).getWsResponse();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all eventtypes", response = EventTypeDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "returns all eventtypes in all languages"),
        @ApiResponse(code = 500, message = "Could not get the evnttypes")})
    public WsResponse getAll() {
        return super.getAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new eventtype", response = String.class, nickname = "create")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the created EventType", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse insert(
            @ApiParam(value = "The eventtype description", required = true) @QueryParam("description") String description,
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
        @ApiResponse(code = 200, message = "Returns the id of the updated eventtype"),
        @ApiResponse(code = 500, message = "Internal server error")})
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
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates an eventtype", response = String.class, nickname = "update", notes = "This Method is not supported yet")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(
            @ApiParam(value = "The new descripton of the EventType", required = true) String description,
            @ApiParam(value = "The id of the EventType", required = true) @PathParam("id") String id) {
        return eventTypeDao.updateEventTypeDescription(description, id).getWsResponse();
    }

    @DELETE
    @Path("language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "DELETE", value = "Deletes an eventtyes language by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse deleteLanguage(@PathParam("id") Long id) {
        return eventTypeText.delete(id).getWsResponse();
    }

    @PUT
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates an events language type", response = String.class, nickname = "update", notes = "This Method is not supported yet")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse updateLanguage(
            @ApiParam(value = "The new descripton of the EventType", required = true) String description,
            @ApiParam(value = "The id of the EventType", required = true) @PathParam("id") String id,
            @ApiParam(value = "The id of the EventType", required = true) @PathParam("name") String name) {
        return eventTypeText.updateEventTypeNameByEventTextId(name, id).getWsResponse();
    }

}
