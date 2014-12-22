/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.List;
import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.UsersDao;
import se.geomarket.backend.geomarket.dto.UsersDto;
import se.geomarket.backend.geomarket.dto.summary.UserSummaryDto;
import se.geomarket.backend.geomarket.entity.Users;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;
import se.geomarket.backend.geomarket.mapper.UsersMapper;
import se.geomarket.backend.geomarket.mapper.summary.UserSummaryMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class UsersDaoBean extends BaseDaoBean<Users, UsersDto> implements UsersDao<Users, UsersDto> {

    public UsersDaoBean() {
        super(Users.class);
    }

    @Override
    public Response create(UsersDto dto) {
        Response<Users> entity = UsersMapper.getInstance().mapFromDtoToEntity(dto);
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

}
