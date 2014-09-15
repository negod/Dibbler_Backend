/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.UserDto;
import se.geomarket.backend.geomarket.entity.User;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class UserMapper extends BaseMapper<UserDto, User> {

    private static final UserMapper INSTANCE = new UserMapper();

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFromDtoToEntity(UserDto dto) {
        Mapper mapper = new DozerBeanMapper();
        User destObject = mapper.map(dto, User.class);
        return destObject;
    }

    @Override
    public UserDto mapFromEntityToDto(User entity) {
        Mapper mapper = new DozerBeanMapper();
        UserDto destObject = mapper.map(entity, UserDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(User entity, UserDto dto) {
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.getGender());
        entity.setImageUrl(dto.getImageUrl());
        entity.setPassword(dto.getPassword());
        entity.setUpdatedDate(new Date());       
    }

}
