/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.RolesDto;
import se.geomarket.backend.geomarket.entity.Roles;
import se.geomarket.backend.geomarket.generics.BaseMapper;

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
