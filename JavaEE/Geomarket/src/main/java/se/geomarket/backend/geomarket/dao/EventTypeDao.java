/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao;

import java.util.List;
import javax.ejb.Local;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.generics.BaseDao;
import se.geomarket.backend.geomarket.generics.BaseDto;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 * @param <E>
 * @param <D>
 */
@Local
public interface EventTypeDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D>{
    
    public String addLanguage(String categoryId, String name, String language);

    public List<NameSummaryDto> getEventTypesByLanguage(String languageId);
    
}
