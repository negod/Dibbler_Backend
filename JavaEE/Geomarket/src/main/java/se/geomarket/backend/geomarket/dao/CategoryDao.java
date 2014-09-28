/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao;

import java.util.List;
import javax.ejb.Local;
import se.geomarket.backend.geomarket.dto.languagesupport.NameDto;
import se.geomarket.backend.geomarket.generics.BaseDao;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 * @param <E>
 */
@Local
public interface CategoryDao<E extends BaseEntity> extends BaseDao<E> {

    public String addLanguage(String categoryId, String name, String language);
    
    public List<String> getCategoriesByLanguage(String languageId);

}
