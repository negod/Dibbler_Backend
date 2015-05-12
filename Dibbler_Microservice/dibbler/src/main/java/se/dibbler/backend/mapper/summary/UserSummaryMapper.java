/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper.summary;

import se.dibbler.backend.dto.summary.UserSummaryDto;
import se.dibbler.generic.dao.BaseMapper;
import se.dibbler.generic.user.User;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class UserSummaryMapper extends BaseMapper<UserSummaryDto, User> {

    private static final UserSummaryMapper INSTANCE = new UserSummaryMapper();

    public static UserSummaryMapper getInstance() {
        return INSTANCE;
    }

    public UserSummaryMapper() {
        super(UserSummaryDto.class, User.class);
    }

}
