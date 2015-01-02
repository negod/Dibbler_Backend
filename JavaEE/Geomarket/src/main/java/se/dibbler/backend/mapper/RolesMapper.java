/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.RolesDto;
import se.dibbler.backend.entity.Roles;
import se.dibbler.backend.generics.BaseMapper;

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
