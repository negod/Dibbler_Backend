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
import se.dibbler.backend.dao.LocationDao;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.entity.Location;
import se.dibbler.generic.dao.ErrorLogDao;
import se.dibbler.generic.dto.ErrorLogDto;
import se.dibbler.generic.error.GenericError;
import se.dibbler.generic.webservice.BaseWs;
import se.dibbler.generic.webservice.WsResponse;

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

    @POST
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(
            LocationDto data,
            @PathParam("companyId") String companyId) {
        try {
            return locationDao.addLocationToCompany(data, companyId).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @PUT
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse update(
            LocationDto data,
            @PathParam("companyId") String companyId) {
        try {
            return locationDao.updateLocationInCompany(data, companyId).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @DELETE
    @Path("/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse delete(
            @PathParam("companyId") String companyId,
            @QueryParam("locationId") String locationId) {
        try {
            return locationDao.removeLocationInCompany(locationId, companyId).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

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
