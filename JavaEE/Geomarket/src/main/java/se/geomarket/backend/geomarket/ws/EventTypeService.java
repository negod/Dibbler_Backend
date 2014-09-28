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
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.Response;
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.EventTypeName;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.EventTypeMapper;
import se.geomarket.backend.geomarket.utils.EntityUtils;
import se.geomarket.backend.geomarket.utils.ResponseUtil;

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
    LanguageDao languageDao;

    @Override
    public EventTypeDao getDao() {
        return eventTypeDao;
    }

    @Override
    public BaseMapper<EventTypeDto, EventType> getMapper() {
        return EventTypeMapper.getInstance();
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a EventType by Id", response = EventTypeDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a EventType"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("{languageId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets all Categorytypes in a certain language", response = NameSummaryDto.class, nickname = "getAllByLanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a EventType"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getAllByLanguage(@PathParam("languageId") String languageId) {
        List<NameSummaryDto> categories = eventTypeDao.getEventTypesByLanguage(languageId);
        return Response.ok(categories).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Categories", response = EventTypeDto.class, nickname = "getAll", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All Categories found"),
        @ApiResponse(code = 500, message = "Could not get the categories")})
    public Response getAll() {
        return super.getAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new EventType", response = String.class, nickname = "create", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response insert(
            @ApiParam(value = "The EventType description", required = true) @QueryParam("description") String descr,
            @ApiParam(value = "The default name of the EventType", required = true) @QueryParam("defaultName") String defName,
            @ApiParam(value = "The id of the default language", required = true) @QueryParam("languageId") String language) {

        try {

            Language languageEntity = (Language) languageDao.getByExtId(language);

            EventType eventType = new EventType();
            eventType.setDescription(descr);
            eventType.setDefaultName(defName);

            List<EventTypeName> eventNames = new ArrayList<>();
            EventTypeName eventTypeName = (EventTypeName) EntityUtils.setEntityCreateData(new EventTypeName());
            eventTypeName.setLanguage(languageEntity);
            eventTypeName.setName(defName);
            eventTypeName.setEventType(eventType);
            eventNames.add(eventTypeName);

            eventType.setNames(eventNames);

            super.insert(eventType);

            return Response.ok(eventType.getExtId()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addlanguage")
    @ApiOperation(httpMethod = "POST", value = "Adds a description to the EventType in the specified language", response = String.class, nickname = "addlanguage", notes = "Returns the id of the updated EventType")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated EventType"),
        @ApiResponse(code = 500, message = "Could not update categories")})
    public Response addLanguage(
            @ApiParam(value = "The id for the EventType that the new language will be added to", required = true) @QueryParam("categoryId") String categoryId,
            @ApiParam(value = "The name of the EventType in a new language", required = true) @QueryParam("name") String name,
            @ApiParam(value = "The id of the language to add", required = true) @QueryParam("languageId") String language) {
        try {
            return Response.ok(eventTypeDao.addLanguage(categoryId, name, language)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a EventType bi Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Updates a EventType", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response update(
            @ApiParam(value = "The new EventType data", required = true) EventTypeDto data,
            @ApiParam(value = "The id of the EventType", required = true) @PathParam("id") String id) {
        return ResponseUtil.getMethodNotSupportedError();
    }

}
