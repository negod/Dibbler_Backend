/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import java.util.Date;
import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.dto.PointDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyMapper extends BaseMapper<CompanyDto, Company> {

    private static final CompanyMapper INSTANCE = new CompanyMapper();
    private static final GeometryFactory geometryFactory = new GeometryFactory();

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
            //Point p = geometryFactory.createPoint(new Coordinate(dto.getLocation().getLatitude(), dto.getLocation().getLongitude()));
            //entity.setLocation(p);
            return entity;
        } catch (Exception e) {
            logger.debug("[ Failed to map from dto {} to entity {} [ DTO EXT_ID: {} ]", dto.getClass().getName(), Company.class.getName(), dto.getExtId());
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
            PointDto point = new PointDto();
            //point.setLatitude(entity.getLocation().getCoordinate().x);
            //point.setLongitude(entity.getLocation().getCoordinate().y);
            dto.setLocation(point);
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
