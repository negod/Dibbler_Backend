/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper.summary;

import se.geomarket.backend.geomarket.dto.summary.UserSummaryDto;
import se.geomarket.backend.geomarket.entity.Users;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class UserSummaryMapper extends BaseMapper<UserSummaryDto, Users> {

    private static final UserSummaryMapper INSTANCE = new UserSummaryMapper();

    public static UserSummaryMapper getInstance() {
        return INSTANCE;
    }

    public UserSummaryMapper() {
        super(UserSummaryDto.class, Users.class);
    }

}
