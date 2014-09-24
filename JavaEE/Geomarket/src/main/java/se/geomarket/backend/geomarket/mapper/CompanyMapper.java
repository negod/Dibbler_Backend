/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import com.vividsolutions.jts.geom.GeometryFactory;
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
            entity.setCity(dto.getCity());
            entity.setCountry(dto.getCity());
            entity.setName(dto.getName());
            entity.setPostalCode(dto.getPostalCode());
            entity.setState(dto.getState());
            entity.setStreet(dto.getStreet());
            entity.setStreetNr(dto.getStreetNr());
            Location location = new Location();
            location.setLatitude(dto.getLocation().getLatitude());
            location.setLongitude(dto.getLocation().getLongitude());
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
            dto.setCity(entity.getCity());
            dto.setCountry(entity.getCity());
            dto.setName(entity.getName());
            dto.setPostalCode(entity.getPostalCode());
            dto.setState(entity.getState());
            dto.setStreet(entity.getStreet());
            dto.setStreetNr(entity.getStreetNr());
            LocationDto location = new LocationDto();
            location.setLatitude(entity.getLocation().getLatitude());
            location.setLongitude(entity.getLocation().getLongitude());
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
