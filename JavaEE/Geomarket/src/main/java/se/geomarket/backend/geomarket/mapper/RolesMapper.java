/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.RolesDto;
import se.geomarket.backend.geomarket.dto.UsersDto;
import se.geomarket.backend.geomarket.entity.Roles;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class RolesMapper extends BaseMapper<RolesDto, Roles>{
    
    private static final RolesMapper INSTANCE = new RolesMapper();

    private RolesMapper() {
    }

    public static RolesMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Roles mapFromDtoToEntity(RolesDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Roles destObject = mapper.map(dto, Roles.class);
        return destObject;
    }

    @Override
    public RolesDto mapFromEntityToDto(Roles entity) {
        Mapper mapper = new DozerBeanMapper();
        RolesDto destObject = mapper.map(entity, RolesDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Roles entity, RolesDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
