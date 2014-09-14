/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper.impl;

import javax.ejb.Stateless;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.UserDto;
import se.geomarket.backend.geomarket.entity.User;
import se.geomarket.backend.geomarket.mapper.DtoMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class DtoMapperImpl implements DtoMapper {

    @Override
    public UserDto mapUserDto(User entity) {
        Mapper mapper = new DozerBeanMapper();
        UserDto destObject = mapper.map(entity, UserDto.class);
        return destObject;
    }

}
