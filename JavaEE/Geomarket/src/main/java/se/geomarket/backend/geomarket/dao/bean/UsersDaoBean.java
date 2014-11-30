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
import se.geomarket.backend.geomarket.generics.MethodResponse;
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
    public MethodResponse create(UsersDto dto) {
        MethodResponse<Users> entity = UsersMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return MethodResponse.error(entity.getErrorCode());
        }
        return super.create(entity.getData());
    }

    @Override
    public MethodResponse<UserSummaryDto> getUserSummaryById(String id) {
        MethodResponse<Users> entity = super.getByExtId(id);
        if (entity.hasErrors) {
            return MethodResponse.error(entity.getErrorCode());
        }
        return UserSummaryMapper.getInstance().mapFromEntityToDto(entity.getData());
    }

    @Override
    public MethodResponse<List<UserSummaryDto>> getAllUserSummary() {
        MethodResponse<List<Users>> entityList = super.getAll();
        if (entityList.hasErrors) {
            return MethodResponse.error(entityList.getErrorCode());
        }
        return UserSummaryMapper.getInstance().mapToDtoList(entityList.getData());
    }

}
