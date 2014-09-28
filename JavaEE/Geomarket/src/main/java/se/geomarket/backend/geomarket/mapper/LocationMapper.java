/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.LocationDto;
import se.geomarket.backend.geomarket.entity.Location;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class LocationMapper extends BaseMapper<LocationDto, Location> {
    
    private static final LocationMapper INSTANCE = new LocationMapper();
    
    private LocationMapper() {
    }
    
    public static LocationMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Location mapFromDtoToEntity(LocationDto dto) {
        Location entity = new Location();
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());
        return entity;
    }
    
    @Override
    public LocationDto mapFromEntityToDto(Location entity) {
        LocationDto dto = new LocationDto();
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setName(entity.getName());
        return dto;
    }
    
    @Override
    public void updateEntityFromDto(Location entity, LocationDto dto) {
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setName(dto.getName());
    }
    
}
