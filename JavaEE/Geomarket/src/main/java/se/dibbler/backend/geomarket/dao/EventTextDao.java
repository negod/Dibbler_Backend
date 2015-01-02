/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao;

import javax.ejb.Local;
import se.dibbler.backend.geomarket.dto.summary.EventTextSummaryDto;
import se.dibbler.backend.geomarket.generics.BaseDao;
import se.dibbler.backend.geomarket.generics.BaseDto;
import se.dibbler.backend.geomarket.generics.BaseEntity;
import se.dibbler.backend.geomarket.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 */
@Local
public interface EventTextDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    Response<EventTextSummaryDto> getEventTextSummaryByLanguage(String languageId);

}
