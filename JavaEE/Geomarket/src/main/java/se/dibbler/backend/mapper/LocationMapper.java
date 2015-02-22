/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class LocationMapper extends BaseMapper<LocationDto, Location> {

    private static final LocationMapper INSTANCE = new LocationMapper();

    public static LocationMapper getInstance() {
        return INSTANCE;
    }

    public LocationMapper() {
        super(LocationDto.class, Location.class);
    }

}
