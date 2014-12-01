/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
import se.geomarket.backend.geomarket.dao.RolesDao;
import se.geomarket.backend.geomarket.dto.RolesDto;
import se.geomarket.backend.geomarket.entity.Roles;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.generics.WsResponse;
import se.geomarket.backend.geomarket.mapper.RolesMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/roles")
@Api(value = "/roles", description = "Handles all roles", hidden = true)
public class RolesService extends BaseWs<RolesDto, Roles, RolesDao> {

    @EJB
    RolesDao roleDao;

    @Override
    public RolesDao getDao() {
        return roleDao;
    }

    @Override
    public BaseMapper<RolesDto, Roles> getMapper() {
        return RolesMapper.getInstance();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new Role", response = String.class, nickname = "insert", hidden = true)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created Role", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse insert(RolesDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a Role by Id", response = RolesDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Role"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a Role by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Update a Role", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the Role"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(RolesDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Roles", response = RolesDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All Roles found"),
        @ApiResponse(code = 500, message = "Could not get the Roles")})
    @Override
    public WsResponse getAll() {
        return super.getAll();
    }

}
