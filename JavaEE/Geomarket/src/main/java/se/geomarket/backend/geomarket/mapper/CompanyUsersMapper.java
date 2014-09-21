/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.CompanyUsersDto;
import se.geomarket.backend.geomarket.entity.CompanyUsers;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyUsersMapper extends BaseMapper<CompanyUsersDto, CompanyUsers> {
    
    private static final CompanyUsersMapper INSTANCE = new CompanyUsersMapper();
    
    private CompanyUsersMapper() {
    }
    
    public static CompanyUsersMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public CompanyUsers mapFromDtoToEntity(CompanyUsersDto dto) {
        Mapper mapper = new DozerBeanMapper();
        CompanyUsers destObject = mapper.map(dto, CompanyUsers.class);
        return destObject;
    }
    
    @Override
    public CompanyUsersDto mapFromEntityToDto(CompanyUsers entity) {
        Mapper mapper = new DozerBeanMapper();
        CompanyUsersDto destObject = mapper.map(entity, CompanyUsersDto.class);
        return destObject;
    }
    
    @Override
    public void updateEntityFromDto(CompanyUsers entity, CompanyUsersDto dto) {
        entity.setUpdatedDate(new Date());
        entity.setCompany(CompanyMapper.getInstance().mapFromDtoToEntity(dto.getCompany()));
        entity.setUserRole(RolesMapper.getInstance().mapFromDtoToEntity(dto.getUserRole()));
        entity.setUpdatedDate(new Date());
    }
    
}
