/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dibbler.backend.geomarket.mapper;

import se.dibbler.backend.geomarket.dto.RolesDto;
import se.dibbler.backend.geomarket.entity.Roles;
import se.dibbler.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class RolesMapper extends BaseMapper<RolesDto, Roles>{
    
    private static final RolesMapper INSTANCE = new RolesMapper();

    public static RolesMapper getInstance() {
        return INSTANCE;
    }

    public RolesMapper() {
        super(RolesDto.class, Roles.class);
    }
    
}
