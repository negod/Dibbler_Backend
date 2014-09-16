/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyMapper extends BaseMapper<CompanyDto, Company> {
    
    private static final CompanyMapper INSTANCE = new CompanyMapper();
    
    private CompanyMapper() {
    }
    
    public static CompanyMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Company mapFromDtoToEntity(CompanyDto dto) {
        try {
            Mapper mapper = new DozerBeanMapper();
            Company destObject = mapper.map(dto, Company.class);
            return destObject;
        } catch (Exception e) {
            logger.debug("[ Failed to map from dto {} to entity {} [ DTO EXT_ID: {} ]", dto.getClass().getName(), Company.class.getName(), dto.getExtId());
            return null;
        }
    }
    
    @Override
    public CompanyDto mapFromEntityToDto(Company entity) {
        try {
            Mapper mapper = new DozerBeanMapper();
            CompanyDto destObject = mapper.map(entity, CompanyDto.class);
            return destObject;
        } catch (Exception e) {
            logger.debug("[ Failed to map from entity {} to dto {} [ ETITY_ID: {} ] ", entity.getClass().getName(), CompanyDto.class.getName(), entity.getId());
            return null;
        }
    }
    
    @Override
    public void updateEntityFromDto(Company entity, CompanyDto dto) {
        try {
            entity.setCity(dto.getCity());
            entity.setCountry(dto.getCountry());
            entity.setFollowerClaim(dto.getFollowerClaim());
            entity.setIdNr(dto.getIdNr());
            entity.setName(dto.getName());
            entity.setPostalCode(dto.getPostalCode());
            entity.setState(dto.getState());
            entity.setStreet(dto.getStreet());
            entity.setUpdatedDate(new Date());
        } catch (Exception e) {
            logger.debug("[ Failed to update entity {} from dto {} [ ETITY_ID: {} ]", entity.getClass().getName(), dto.getClass().getName(), entity.getId());
        }
    }
    
}
