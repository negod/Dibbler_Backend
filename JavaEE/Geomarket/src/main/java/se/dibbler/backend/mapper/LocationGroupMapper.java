/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.LocationGroupDto;
import se.dibbler.backend.entity.LocationGroup;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class LocationGroupMapper extends BaseMapper<LocationGroupDto, LocationGroup> {

    private static final LocationGroupMapper INSTANCE = new LocationGroupMapper();

    public static LocationGroupMapper getInstance() {
        return INSTANCE;
    }

    public LocationGroupMapper() {
        super(LocationGroupDto.class, LocationGroup.class);
    }

}
