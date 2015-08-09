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

import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dto.ErrorLogDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("users")
public class UsersService extends BaseWs<UsersDto, Users, UsersDao> {

    @EJB
    private UsersDao userDao;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public UsersDao getDao() {
        return userDao;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(UsersDto data) {
        try {
            // If user has signed up with facebook or google before, we just return the id of that user
            Response<UserSummaryDto> socialUser = getFacebookOrGoogleUser(data.getFacebookId(), data.getGoogleId());
            if (socialUser.hasNoErrors) {
                return new WsResponse<String>(socialUser.getData().getId(), 200);
            }
            return super.insert(data);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @POST
    @Path("/authenticate")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse authenticate(AuthUserDto data) {
        try {
            // If user has signed up with facebook or google before, we just return the id of that user
            Response<UserSummaryDto> socialUser = getFacebookOrGoogleUser(data.getFacebookId(), data.getGoogleId());
            if (socialUser.hasNoErrors) {
                return socialUser.getWsResponse();
            }
            // TODO Password should not be sent as plain text!
            return getErrorLog().createLog(getDao().authenticate(data.getUsername(), data.getPassword())).getWsResponse();
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
            return getErrorLog().createLog(getDao().getUserSummaryById(id)).getWsResponse();
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
    public WsResponse update(UserSummaryDto data, @PathParam("id") String id) {
        try {
            UsersDto dto = Mapper.getInstance().getMapper().map(data, UsersDto.class);
            return super.update(dto, id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getAll() {
        try {
            return getErrorLog().createLog(getDao().getAllUserSummary()).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    @PUT
    @Path("/changePass/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse changePassword(@PathParam("id") String id, @QueryParam("old") String oldPass, @QueryParam("new") String newPass) {
        try {
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
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }

    }

    private Response<UserSummaryDto> getFacebookOrGoogleUser(String facebookId, String googleId) {
        if (facebookId != null) {
            Response<UserSummaryDto> fbUser = getDao().getUserByFacebookId(facebookId);
            if (fbUser.hasNoErrors && fbUser.dataIsNotNull) {
                return fbUser;
            }
        } else if (googleId != null) {
            Response<UserSummaryDto> googleUser = getDao().getUserByGoogleId(googleId);
            if (googleUser.hasNoErrors && googleUser.dataIsNotNull) {
                return googleUser;
            }
        }
        return Response.error(GenericError.NO_RESULT);
    }

}
