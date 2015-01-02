/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.mapper;

import se.dibbler.backend.geomarket.dto.UsersDto;
import se.dibbler.backend.geomarket.entity.Users;
import se.dibbler.backend.geomarket.generics.BaseMapper;

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
