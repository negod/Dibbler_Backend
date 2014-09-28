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
import org.hibernate.search.query.dsl.Unit;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dao.EventDao;
import se.geomarket.backend.geomarket.dto.EventDto;
import se.geomarket.backend.geomarket.dto.summary.EventSummaryDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.EventMapper;
import se.geomarket.backend.geomarket.mapper.summary.EventSummaryMapper;
import se.geomarket.backend.geomarket.utils.ResponseUtil;

/**
 *
 * @author Joakim
 */
@Stateless
@Path("/Events")
@Api(value = "/Events", description = "Handles all Events")
public class EventService extends BaseWs<EventDto, Event, EventDao> {

    @EJB
    CompanyDao companyDao;
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
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new Events", response = String.class, nickname = "insert", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created Event"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response insert(EventDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets an Event by Id", response = EventDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Event"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("byLocation")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets all Events based on the users position", response = EventSummaryDto.class, nickname = "getEventsByLocation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a list of Events"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getEventsByLocation(
            @ApiParam(value = "The present longitude", required = true) @QueryParam("longitude") Double longitude,
            @ApiParam(value = "The present latitude", required = true) @QueryParam("latitude") Double latitude,
            @ApiParam(value = "How large area of Events that should return ( In KM )", required = true) @QueryParam("radius") Double radius,
            @ApiParam(value = "The default language", required = true) @QueryParam("language") String languageId) {
        try {
            List<Company> locations = companyDao.getCompanyByLocation(longitude, latitude, radius, Unit.KM);
            List<EventSummaryDto> allEvents = new ArrayList<>();
            for (Company company : locations) {
                allEvents.addAll(EventSummaryMapper.getInstance().extractEvents(company));
            }
            return Response.ok(allEvents).build();
        } catch (Exception e) {
            return ResponseUtil.getErrorMessage();
        }

    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Delete an Event", response = EventDto.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Event deleted"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Update an Event", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the Event"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response update(EventDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Get all Events", response = EventDto.class, nickname = "get All")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the Event"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getAll() {
        return super.getAll();
    }

}