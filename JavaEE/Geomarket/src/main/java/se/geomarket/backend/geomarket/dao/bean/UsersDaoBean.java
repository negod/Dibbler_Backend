/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.UsersDao;
import se.geomarket.backend.geomarket.dto.UsersDto;
import se.geomarket.backend.geomarket.entity.Users;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.DaoResponse;
import se.geomarket.backend.geomarket.mapper.UsersMapper;

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
    public DaoResponse create(UsersDto dto) {
        return super.create(UsersMapper.getInstance().mapFromDtoToEntity(dto));
    }

}
