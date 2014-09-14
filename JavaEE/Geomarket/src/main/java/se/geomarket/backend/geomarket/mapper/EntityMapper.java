/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.mapper;

import javax.ejb.Local;
import se.geomarket.backend.geomarket.dto.UserDto;
import se.geomarket.backend.geomarket.entity.User;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Local
public interface EntityMapper {
    
    public User mapUserEntity(UserDto dto);
    
    public User mapUserEntity(User dto);
    
}
