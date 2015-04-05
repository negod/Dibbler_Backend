/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.dao.UsersDao;
import se.dibbler.backend.dto.UsersDto;
import se.dibbler.backend.dto.summary.UserSummaryDto;
import se.dibbler.backend.entity.Users;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.summary.UserSummaryMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class UsersDaoBean extends BaseDaoBean<Users, UsersDto> implements UsersDao<Users, UsersDto> {

    public UsersDaoBean() {
        super(Users.class, UsersDto.class);
    }

    @Override
    public Response create(UsersDto dto) {
        Response<Users> entity = super.mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return super.create(entity.getData());
    }

    @Override
    public Response<UserSummaryDto> getUserSummaryById(String id) {
        Response<Users> entity = super.getByExtId(id);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return UserSummaryMapper.getInstance().mapFromEntityToDto(entity.getData());
    }

    @Override
    public Response<List<UserSummaryDto>> getAllUserSummary() {
        Response<List<Users>> entityList = super.getAll();
        if (entityList.hasErrors) {
            return Response.error(entityList.getError());
        }
        return UserSummaryMapper.getInstance().mapToDtoList(entityList.getData());
    }

    @Override
    public Response<String> update(UsersDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<UserSummaryDto> authenticate(String username, String password) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        Response<Users> user = super.getSingleByNamedQuery(DibblerNamedQueries.USERS_AUTHENTICATE, params);
        if (user.hasErrors) {
            return Response.error(user.getError());
        }
        return UserSummaryMapper.getInstance().mapFromEntityToDto(user.getData());
    }

    @Override
    public Response<UserSummaryDto> getUserByFacebookId(String facebookId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("facebookId", facebookId);
        Response<Users> user = super.getSingleByNamedQuery(DibblerNamedQueries.USERS_FINDBY_FACEBOOK_ID, params);
        if (user.hasErrors) {
            return Response.error(user.getError());
        }
        return UserSummaryMapper.getInstance().mapFromEntityToDto(user.getData());
    }

    @Override
    public Response<UserSummaryDto> getUserByGoogleId(String googleId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("googleId", googleId);
        Response<Users> user = super.getSingleByNamedQuery(DibblerNamedQueries.USERS_FINDBY_GOOGLE_ID, params);
        if (user.hasErrors) {
            return Response.error(user.getError());
        }
        return UserSummaryMapper.getInstance().mapFromEntityToDto(user.getData());
    }

}
