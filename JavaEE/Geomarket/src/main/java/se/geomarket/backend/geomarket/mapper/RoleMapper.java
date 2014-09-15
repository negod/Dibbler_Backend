/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.RoleDto;
import se.geomarket.backend.geomarket.dto.UserDto;
import se.geomarket.backend.geomarket.entity.Role;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class RoleMapper extends BaseMapper<RoleDto, Role>{
    
    private static final RoleMapper INSTANCE = new RoleMapper();

    private RoleMapper() {
    }

    public static RoleMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Role mapFromDtoToEntity(RoleDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Role destObject = mapper.map(dto, Role.class);
        return destObject;
    }

    @Override
    public RoleDto mapFromEntityToDto(Role entity) {
        Mapper mapper = new DozerBeanMapper();
        RoleDto destObject = mapper.map(entity, RoleDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Role entity, RoleDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
