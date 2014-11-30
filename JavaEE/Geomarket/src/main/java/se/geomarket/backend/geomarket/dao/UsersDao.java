/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao;

import java.util.List;
import javax.ejb.Local;
import se.geomarket.backend.geomarket.dto.summary.UserSummaryDto;
import se.geomarket.backend.geomarket.generics.BaseDao;
import se.geomarket.backend.geomarket.generics.BaseDto;
import se.geomarket.backend.geomarket.generics.BaseEntity;
import se.geomarket.backend.geomarket.generics.MethodResponse;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 */
@Local
public interface UsersDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public MethodResponse<UserSummaryDto> getUserSummaryById(String id);

    MethodResponse<List<UserSummaryDto>> getAllUserSummary();

}
