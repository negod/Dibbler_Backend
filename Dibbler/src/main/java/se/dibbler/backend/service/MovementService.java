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
import javax.ws.rs.core.MediaType;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dao.MovementDao;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.dto.MovementDto;
import se.dibbler.backend.entity.Movement;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/movements")
public class MovementService extends BaseWs<MovementDto, Movement, MovementDao> {

    @EJB
    MovementDao movementDao;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public MovementDao getDao() {
        return movementDao;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(MovementDto data) {
        try {
            return super.insert(data);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getById(@PathParam("id") String id) {
        try {
            return super.getById(id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse delete(@PathParam("id") Long id) {
        try {
            return super.delete(id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse update(MovementDto data, @PathParam("id") String id) {
        try {
            return super.update(data, id);
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
