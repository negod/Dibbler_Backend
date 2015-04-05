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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import se.dibbler.backend.dao.UsersDao;
import se.dibbler.backend.dto.AuthUserDto;
import se.dibbler.backend.dto.UsersDto;
import se.dibbler.backend.dto.summary.UserSummaryDto;
import se.dibbler.backend.entity.Users;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Mapper;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.generics.WsResponse;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

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
        // If user has signed up with facebook or google before, we just return the id of that user
        Response<UserSummaryDto> socialUser = getFacebookOrGoogleUser(data.getFacebookId(), data.getGoogleId());
        if (socialUser.hasNoErrors) {
            return new WsResponse<String>(socialUser.getData().getId(), 200);
        }
        return super.insert(data);
    }
    
    @POST
    @Path("/authenticate")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Authenticates a User", response = String.class, nickname = "authenticate")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a user object", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse authenticate(AuthUserDto data) {
        // If user has signed up with facebook or google before, we just return the id of that user
        Response<UserSummaryDto> socialUser = getFacebookOrGoogleUser(data.getFacebookId(), data.getGoogleId());
        if (socialUser.hasNoErrors) {
            return socialUser.getWsResponse();
        }
        // TODO Password should not be sent as plain text!
        return getDao().authenticate(data.getUsername(), data.getPassword()).getWsResponse();
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a User by Id", response = UserSummaryDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a User"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
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
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1004, message = "Error when deleting from database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
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
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse update(UserSummaryDto data, @PathParam("id") String id) {
        UsersDto dto = Mapper.getInstance().getMapper().map(data, UsersDto.class);
        return super.update(dto, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all users", response = UsersDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns all users"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    @Override
    public WsResponse getAll() {
        return getDao().getAllUserSummary().getWsResponse();
    }

    @PUT
    @Path("/changePass/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Change the password for a user", response = String.class, nickname = "changePassword")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated User"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 701, message = "Wrong parameters or null in request"),
        @ApiResponse(code = 701, message = "Old password not same!"),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse changePassword(@PathParam("id") String id, @QueryParam("old") String oldPass, @QueryParam("new") String newPass) {

        if (oldPass == null || newPass == null) {
            return Response.error(GenericError.WRONG_PARAMETER).getWsResponse();
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
    
    private Response<UserSummaryDto> getFacebookOrGoogleUser(String facebookId, String googleId) {
        if (facebookId != null) {
            Response<UserSummaryDto> fbUser = getDao().getUserByFacebookId(facebookId);
            if (fbUser.hasNoErrors && fbUser.getData() != null) {
                return fbUser;
            }
        } else if (googleId != null) {
            Response<UserSummaryDto> googleUser = getDao().getUserByGoogleId(googleId);
            if (googleUser.hasNoErrors && googleUser.getData() != null) {
                return googleUser;
            }
        }
        return Response.error(GenericError.NO_RESULT);
    }

}
