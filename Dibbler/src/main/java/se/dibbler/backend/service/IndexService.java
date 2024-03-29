/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import se.dibbler.backend.constants.IndexingClasses;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dao.bean.IndexDaoBean;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/indexes")
public class IndexService {

    @EJB
    IndexDaoBean indexer;

    @EJB
    ErrorLogDao errorLog;

    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse reindex(@QueryParam(value = "class") IndexingClasses className) {
        try {
            indexer.reIndex(className.getClassToIndex());
            return Response.success("Reindexing complete without errors").getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

}
