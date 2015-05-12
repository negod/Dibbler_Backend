/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.dto.summary.NameSummaryDto;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDao;
import se.dibbler.generic.dto.BaseDto;
import se.dibbler.generic.entity.BaseEntity;

/**
 *
 * @author Joakim
 * @param <E>
 * @param <D>
 */
@Local
public interface CategoryDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addLanguage(String categoryId, String name, String language);

    public Response<List<NameSummaryDto>> getCategoriesByLanguage(String languageId);

    public Response<String> updateCategoryDescription(String description, String eventTypeNameId);

}
