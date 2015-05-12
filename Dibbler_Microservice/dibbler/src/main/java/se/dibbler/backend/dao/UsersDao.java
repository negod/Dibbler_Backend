/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;

import javax.ejb.Local;

import se.dibbler.backend.dto.summary.UserSummaryDto;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDao;
import se.dibbler.generic.dto.BaseDto;
import se.dibbler.generic.entity.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 */
@Local
public interface UsersDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<UserSummaryDto> getUserSummaryById(String id);

    Response<List<UserSummaryDto>> getAllUserSummary();

    Response<UserSummaryDto> authenticate(String username, String password);

    Response<UserSummaryDto> getUserByFacebookId(String facebookId);

    Response<UserSummaryDto> getUserByGoogleId(String googleId);

}
