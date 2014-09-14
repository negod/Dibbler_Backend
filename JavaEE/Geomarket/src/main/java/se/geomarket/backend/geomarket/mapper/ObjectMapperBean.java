/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class ObjectMapperBean implements ObjectMapper {

    @EJB
    EntityMapper entityMapper;
    @EJB
    DtoMapper dtoMapper;

    @Override
    public DtoMapper getDtoMapper() {
        return dtoMapper;
    }

    @Override
    public EntityMapper getEntityMapper() {
        return entityMapper;
    }

}
