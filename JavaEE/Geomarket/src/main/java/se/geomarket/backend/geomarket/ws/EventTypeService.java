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
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.dto.languagesupport.NameDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.generics.GenericError;
import se.geomarket.backend.geomarket.generics.MethodResponse;
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
        @ApiResponse(code = 200, message = "Returns the id of the created EventType"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse insert(
            @ApiParam(value = "The eventtype description", required = true) @QueryParam("description") String description,
            @ApiParam(value = "The default name of the eventtype", required = true) @QueryParam("defaultName") String defaultName,
            @ApiParam(value = "The id of the default language", required = true) @QueryParam("languageId") String languageId) {
        return super.insert(new EventTypeDto(defaultName, description, languageId));
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
    @Path("{id}")
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
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Updates an eventtype", response = String.class, nickname = "update", notes = "This Method is not supported yet")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(
            @ApiParam(value = "The new EventType data", required = true) EventTypeDto data,
            @ApiParam(value = "The id of the EventType", required = true) @PathParam("id") String id) {
        return MethodResponse.error(GenericError.METHOD_NOT_IMPLEMENTED).getWsResponse();
    }

    @PUT
    @Path("{id}/{languageId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates a category", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(
            @ApiParam(value = "The new EventName data", required = true) NameDto data,
            @ApiParam(value = "The id of the EventType Name", required = true) @PathParam("id") String eventTypeNameId) {
        return eventTypeDao.updateEventTypeName(data, eventTypeNameId).getWsResponse();
    }

}
