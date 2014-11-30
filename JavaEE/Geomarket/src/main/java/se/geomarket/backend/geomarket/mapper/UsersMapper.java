/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.UsersDto;
import se.geomarket.backend.geomarket.entity.Users;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class UsersMapper extends BaseMapper<UsersDto, Users> {

    private static final UsersMapper INSTANCE = new UsersMapper();

    public static UsersMapper getInstance() {
        return INSTANCE;
    }

    public UsersMapper() {
        super(UsersDto.class, Users.class);
    }

}
