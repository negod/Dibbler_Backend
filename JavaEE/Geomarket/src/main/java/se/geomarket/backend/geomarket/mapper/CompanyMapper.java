/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.dto.LocationDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Location;
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
            Company entity = new Company();
            entity.setOrgNr(dto.getOrgNr());
            entity.setName(dto.getName());
            entity.setStreet(dto.getStreet());
            entity.setStreetNr(dto.getStreetNr());
            entity.setCity(dto.getCity());
            entity.setState(dto.getState());
            entity.setCountry(dto.getCountry());
            entity.setPostalCode(dto.getPostalCode());
            entity.setFollowerClaim(dto.getFollowerClaim());
            entity.setWww(dto.getWww());
            Location location = LocationMapper.getInstance().mapFromDtoToEntity(dto.getLocation());
            entity.setLocation(location);
            return entity;
        } catch (Exception e) {
            logger.debug("[ Failed to map from dto {} to entity {} [ DTO EXT_ID: {} ]", dto.getClass().getName(), Company.class.getName(), dto.getId());
            return null;
        }
    }
    
    @Override
    public CompanyDto mapFromEntityToDto(Company entity) {
        try {
            CompanyDto dto = new CompanyDto();
            dto.setId(entity.getExtId());
            dto.setOrgNr(entity.getOrgNr());
            dto.setName(entity.getName());
            dto.setStreet(entity.getStreet());
            dto.setStreetNr(entity.getStreetNr());
            dto.setCity(entity.getCity());
            dto.setState(entity.getState());
            dto.setCountry(entity.getCity());
            dto.setPostalCode(entity.getPostalCode());
            dto.setFollowerClaim(entity.getFollowerClaim());
            dto.setWww(entity.getWww());
            LocationDto location = LocationMapper.getInstance().mapFromEntityToDto(entity.getLocation());
            dto.setLocation(location);
            return dto;
        } catch (Exception e) {
            logger.debug("[ Failed to map from entity {} to dto {} [ ETITY_ID: {} ] Error : {}", entity.getClass().getName(), CompanyDto.class.getName(), entity.getId(), e);
            return null;
        }
    }
    
    @Override
    public void updateEntityFromDto(Company entity, CompanyDto dto) {
        try {
            entity.setCity(dto.getCity());
            entity.setCountry(dto.getCountry());
            entity.setFollowerClaim(dto.getFollowerClaim());
            entity.setOrgNr(dto.getOrgNr());
            entity.setName(dto.getName());
            entity.setPostalCode(dto.getPostalCode());
            entity.setState(dto.getState());
            entity.setStreet(dto.getStreet());
            entity.setWww(dto.getWww());
            LocationMapper.getInstance().updateEntityFromDto(entity.getLocation(), dto.getLocation());
            entity.setUpdatedDate(new Date());
        } catch (Exception e) {
            logger.debug("[ Failed to update entity {} from dto {} [ ETITY_ID: {} ]", entity.getClass().getName(), dto.getClass().getName(), entity.getId());
        }
    }
}
