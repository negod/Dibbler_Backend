/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import javax.ejb.Stateless;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.UserDto;
import se.geomarket.backend.geomarket.entity.User;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Stateless
public class EntityMapperImpl implements EntityMapper {

    @Override
    public User mapUserEntity(UserDto dto) {
        Mapper mapper = new DozerBeanMapper();
        User destObject = mapper.map(dto, User.class);
        return destObject;
    }
    
    @Override
    public User mapUserEntity(User dto) {
        Mapper mapper = new DozerBeanMapper();
        User destObject = mapper.map(dto, User.class);
        return destObject;
    }

}
