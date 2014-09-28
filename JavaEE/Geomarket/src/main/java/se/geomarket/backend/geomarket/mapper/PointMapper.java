/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.PointDto;
import se.geomarket.backend.geomarket.entity.Location;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class PointMapper extends BaseMapper<PointDto, Location> {
    
    private static final PointMapper INSTANCE = new PointMapper();
    
    private PointMapper() {
    }
    
    public static PointMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Location mapFromDtoToEntity(PointDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public PointDto mapFromEntityToDto(Location entity) {
        PointDto p = new PointDto();
        p.setLatitude(entity.getLatitude());
        p.setLongitude(entity.getLongitude());
        return p;
    }
    
    @Override
    public void updateEntityFromDto(Location entity, PointDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
