/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.geomarket.dto.languagesupport.CategoryTextDto;
import se.dibbler.backend.geomarket.entity.LanguageText;
import se.dibbler.backend.geomarket.generics.BaseDao;
import se.dibbler.backend.geomarket.generics.BaseDto;
import se.dibbler.backend.geomarket.generics.BaseEntity;
import se.dibbler.backend.geomarket.generics.Response;

/**
 *
 * @author Joakim
 * @param <E>
 * @param <D>
 */
@Local
public interface CategoryDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addLanguage(String categoryId, String name, String language);

    public Response<List<CategoryTextDto>> getCategoriesByLanguage(String languageId);

    public Response<String> updateCategoryDescription(String description, String eventTypeNameId);

}
