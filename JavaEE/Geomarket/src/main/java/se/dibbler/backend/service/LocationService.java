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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import se.dibbler.backend.dao.LocationDao;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.WsResponse;
import se.dibbler.backend.mapper.LocationMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/locations")
@Api(value = "/locations", description = "Handles all Locations in relation to Company")
public class LocationService extends BaseWs<LocationDto, Location, LocationDao> {

    @EJB
    LocationDao locationDao;

    @Override
    public LocationDao getDao() {
        return locationDao;
    }

    @Override
    public BaseMapper<LocationDto, Location> getMapper() {
        return LocationMapper.getInstance();
    }

    @POST
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Adds a Location to a Company", response = String.class, nickname = "add location to company")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the updated Company", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse insert(LocationDto data, @PathParam("companyId") String companyId) {
        return locationDao.addLocationToCompany(data, companyId).getWsResponse();
    }

}
