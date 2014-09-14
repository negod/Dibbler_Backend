/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.mapper;

import javax.ejb.Local;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Local
public interface ObjectMapper {
    
    DtoMapper getDtoMapper();
    
    EntityMapper getEntityMapper();
    
}
