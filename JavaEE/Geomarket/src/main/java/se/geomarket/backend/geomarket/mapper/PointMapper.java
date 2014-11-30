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

    public static PointMapper getInstance() {
        return INSTANCE;
    }

    public PointMapper() {
        super(PointDto.class, Location.class);
    }

}
