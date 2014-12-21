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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import se.geomarket.backend.geomarket.dao.UsersDao;
import se.geomarket.backend.geomarket.dto.UsersDto;
import se.geomarket.backend.geomarket.dto.summary.UserSummaryDto;
import se.geomarket.backend.geomarket.entity.Users;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.generics.GenericError;
import se.geomarket.backend.geomarket.generics.Mapper;
import se.geomarket.backend.geomarket.generics.Response;
import se.geomarket.backend.geomarket.generics.WsResponse;
import se.geomarket.backend.geomarket.mapper.UsersMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("users")
@Api(value = "/users", description = "Handles all Dibbler users")
public class UsersService extends BaseWs<UsersDto, Users, UsersDao> {

    @Context
    private UriInfo context;
    @EJB
    private UsersDao userDao;

    @Override
    public UsersDao getDao() {
        return userDao;
    }

    @Override
    public BaseMapper<UsersDto, Users> getMapper() {
        return UsersMapper.getInstance();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new User", response = String.class, nickname = "insert")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created User", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse insert(UsersDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a User by Id", response = UserSummaryDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a User"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getById(@PathParam("id") String id) {
        return getDao().getUserSummaryById(id).getWsResponse();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a User by Id", response = String.class, nickname = "delete")
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
    @ApiOperation(httpMethod = "PUT", value = "Update a User", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the User"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(UserSummaryDto data, @PathParam("id") String id) {
        UsersDto dto = Mapper.getInstance().getMapper().map(data, UsersDto.class);
        return super.update(dto, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all users", response = UsersDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All Users found"),
        @ApiResponse(code = 500, message = "Could not get Users")})
    @Override
    public WsResponse getAll() {
        return getDao().getAllUserSummary().getWsResponse();
    }

    @PUT
    @Path("/changePass/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Gets a list of all users", response = String.class, nickname = "changePassword")
    @ApiResponses(value = {
        @ApiResponse(code = 701, message = "Incorrect parameters or null values!"),
        @ApiResponse(code = 701, message = "Old password not same!")})
    public WsResponse changePassword(@PathParam("id") String id, @QueryParam("old") String oldPass, @QueryParam("new") String newPass) {

        if (oldPass == null || newPass == null) {
            return Response.error(GenericError.WRONG_PARAMETER, "Incorrect parameters or null values!").getWsResponse();
        }

        Response<Users> entity = getDao().getByExtId(id);
        if (entity.hasNoErrors) {

            if (entity.getData().getPassword().equals(oldPass)) {
                entity.getData().setPassword(newPass);
                return getDao().update(entity.getData()).getWsResponse();
            } else {
                return Response.error(GenericError.FAILURE, "Old password not same!").getWsResponse();
            }

        } else {
            return entity.getWsResponse();
        }

    }

}
