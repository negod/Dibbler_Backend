/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.UsersDto;
import se.geomarket.backend.geomarket.entity.Users;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class UsersMapper extends BaseMapper<UsersDto, Users> {

    private static final UsersMapper INSTANCE = new UsersMapper();

    private UsersMapper() {
    }

    public static UsersMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Users mapFromDtoToEntity(UsersDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Users destObject = mapper.map(dto, Users.class);
        return destObject;
    }

    @Override
    public UsersDto mapFromEntityToDto(Users entity) {
        Mapper mapper = new DozerBeanMapper();
        UsersDto destObject = mapper.map(entity, UsersDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Users entity, UsersDto dto) {
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.getGender());
        entity.setImageUrl(dto.getImageUrl());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        entity.setUpdatedDate(new Date());
    }

}
