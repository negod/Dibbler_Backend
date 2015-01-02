/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dibbler.backend.geomarket.dao;

import javax.ejb.Local;
import se.dibbler.backend.geomarket.generics.BaseDao;
import se.dibbler.backend.geomarket.generics.BaseDto;
import se.dibbler.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E>
 */

@Local
public interface RolesDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D>{
    
}
