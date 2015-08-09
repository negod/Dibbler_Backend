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
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dao.LocationDao;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/locations")
public class LocationService extends BaseWs<LocationDto, Location, LocationDao> {

    @EJB
    LocationDao locationDao;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public LocationDao getDao() {
        return locationDao;
    }

    /**
     * @inputType se.dibbler.backend.dto.create.LocationDto
     * @summary Creates and adds a location to a Company ( NOT the companies
     * home location, that is done in the Company Service )
     */
    @POST
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(LocationDto data, @PathParam("companyId") String companyId) {
        try {
            return getErrorLog().createLog(locationDao.addLocationToCompany(data, companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Gets all locations attached to a specific company
     */
    @GET
    @Path("/company/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getLocationsByCompanyId(@PathParam("companyId") String companyId) {
        try {
            return getErrorLog().createLog(locationDao.getLocationsByCompanyId(companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Gets all locations attached to a specific locationgroup
     */
    @GET
    @Path("/locationgroup/{locationGroupId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getLocationsByLocationGroupId(@PathParam("locationGroupId") String companyId) {
        try {
            return getErrorLog().createLog(locationDao.getLocationsByCompanyId(companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @inputType se.dibbler.backend.dto.create.LocationDto
     * @summary Updates a Location attached to a specific company
     */
    @PUT
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse update(LocationDto data, @PathParam("companyId") String companyId) {
        try {
            return getErrorLog().createLog(locationDao.updateLocationInCompany(data, companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Deletes a location attached to a specific Company ( The
     * companies home location can not be deleted )
     */
    @DELETE
    @Path("/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse delete(
            @PathParam("companyId") String companyId,
            @QueryParam("locationId") String locationId) {
        try {
            return getErrorLog().createLog(locationDao.removeLocationInCompany(locationId, companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @inputType se.dibbler.backend.dto.create.LocationDto
     * @summary Gets all Locations
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getAll() {
        try {
            return super.getAll();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

}
