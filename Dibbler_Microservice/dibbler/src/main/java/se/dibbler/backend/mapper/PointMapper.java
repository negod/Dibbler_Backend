/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.PointDto;
import se.dibbler.backend.entity.Location;
import se.dibbler.generic.dao.BaseMapper;

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
