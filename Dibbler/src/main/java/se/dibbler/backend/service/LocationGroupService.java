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
import se.dibbler.backend.dao.LocationGroupDao;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.dto.LocationGroupDto;
import se.dibbler.backend.dto.create.LocationGroupCreateDto;
import se.dibbler.backend.entity.LocationGroup;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/locationGroups")
public class LocationGroupService extends BaseWs<LocationGroupDto, LocationGroup, LocationGroupDao> {

    @EJB
    LocationGroupDao locationGroupDao;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public LocationGroupDao getDao() {
        return locationGroupDao;
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.LocationGroupDto>
     * @summary Gets all locationGroups attached to a certain company
     */
    @GET
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getByCompanyId(@PathParam("companyId") String companyId) {
        try {
            return getErrorLog().createLog(locationGroupDao.getLocationGroupsByCompanyId(companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     *
     * @summary Creates a locationgroup for a certain company
     * @inputType se.dibbler.backend.dto.create.LocationGroupCreateDto
     */
    @POST
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(LocationGroupCreateDto data, @PathParam("companyId") String companyId) {
        try {
            return getErrorLog().createLog(locationGroupDao.addLocationGroup(data, companyId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Updates a locationgroup attached to a specific company
     * @inputType se.dibbler.backend.dto.create.LocationGroupCreateDto
     * @param data
     * @param companyId
     * @param groupId
     */
    @PUT
    @Path("{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse update(LocationGroupCreateDto data, @PathParam("companyId") String companyId, @QueryParam("groupId") String groupId) {
        try {
            return getErrorLog().createLog(locationGroupDao.addLocationsToLocationGroup(data, companyId, groupId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Deletes a locationgroup attached to a specific company
     * @return
     */
    @DELETE
    @Path("/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse delete(@PathParam("id") Long id) {
        try {
            return super.delete(id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

}
