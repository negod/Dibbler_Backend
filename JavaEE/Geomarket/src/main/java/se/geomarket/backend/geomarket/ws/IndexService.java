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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.geomarket.backend.geomarket.constants.IndexingClasses;
import se.geomarket.backend.geomarket.dao.bean.IndexDaoBean;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/indexes")
@Api(value = "/indexes", description = "Handles all indexing on the server")
public class IndexService {

    @EJB
    IndexDaoBean indexer;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Reindex Location table", response = String.class, nickname = "reindex")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Reindexing complete wihtout errors", response = String.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response reindex(@ApiParam(value = "The class to index", allowableValues = "Location", required = true) @QueryParam(value = "class") String className) {
        try {
            IndexingClasses clazz = IndexingClasses.fromValue(className);
            indexer.reIndex(clazz.getClassToIndex());
            return Response.ok("Reindexing table Location").build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
