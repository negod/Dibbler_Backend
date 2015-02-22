/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import se.dibbler.backend.dao.LocationGroupDao;
import se.dibbler.backend.dto.LocationGroupDto;
import se.dibbler.backend.dto.create.LocationGroupCreateDto;
import se.dibbler.backend.entity.LocationGroup;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/locationGroups")
@Api(value = "/locationGroups", description = "Handles all LocationGroups in relation to Company")
public class LocationGroupService extends BaseWs<LocationGroupDto, LocationGroup, LocationGroupDao> {

    @EJB
    LocationGroupDao locationGroupDao;

    @Override
    public LocationGroupDao getDao() {
        return locationGroupDao;
    }

    @POST
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Created a LocationGroup", response = String.class, nickname = "add location to company")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the updated Company", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse insert(LocationGroupCreateDto data, @PathParam("companyId") String companyId) {
        return locationGroupDao.addLocationGroup(data, companyId).getWsResponse();
    }

    @PUT
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Adds Locations to specified Locationgroup and/or udates name of the locationgroup", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the Language"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse update(LocationGroupCreateDto data, @PathParam("companyId") String companyId, @QueryParam("groupId") String groupId) {
        return locationGroupDao.addLocationsToLocationGroup(data, companyId, groupId).getWsResponse();
    }

    @DELETE
    @Path("/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a LocationGroup by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1004, message = "Error when deleting from database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

}
