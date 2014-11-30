/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.CategoryNameDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.languagesupport.NameDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.CategoryName;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;

/**
 *
 * @author Joakim
 */
@Stateless
public class CategoryDaoBean extends BaseDaoBean<Category, CategoryDto> implements CategoryDao<Category, CategoryDto> {

    @EJB
    LanguageDao languageDao;
    @EJB
    CategoryNameDao categoryNameDao;

    public CategoryDaoBean() {
        super(Category.class);
    }

    @Override
    public MethodResponse<String> addLanguage(String categoryId, String name, String language) {

        MethodResponse<Language> languageEntity = languageDao.getByExtId(language);
        if (languageEntity.hasErrors) {
            return MethodResponse.error(languageEntity.getErrorCode());
        }

        MethodResponse<Category> category = super.getByExtId(categoryId);
        if (category.hasErrors) {
            return MethodResponse.error(category.getErrorCode());
        }

        try {
            //TODO check that language does not exist in category        
            CategoryName categoryname = new CategoryName();
            categoryname.setLanguage(languageEntity.getData());
            categoryname.setName(name);
            categoryname.setCategory(category.getData());
            category.getData().getNames().add(categoryname);
            return MethodResponse.success(category.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ Error when adding language to category ]", e);
            return MethodResponse.error(DaoError.CATEGORY_ADD_LANGUAGE);
        }
    }

    @Override
    public MethodResponse<List<NameSummaryDto>> getCategoriesByLanguage(String languageId) {

        MethodResponse<List<Category>> allCategories = super.getAll();
        if (allCategories.hasErrors) {
            return MethodResponse.error(allCategories.getErrorCode());
        }

        try {
            List<NameSummaryDto> categoryNames = new ArrayList<>();
            for (Category category : allCategories.getData()) {
                MethodResponse<String> name = getNameByLanguage(category.getNames(), languageId);
                if (name.hasNoErrors) {

                    NameSummaryDto dto = new NameSummaryDto();
                    if (name.getData().isEmpty()) {
                        dto.setName(category.getDefaultName());
                    } else {
                        dto.setName(name.getData());
                    }
                    dto.setId(category.getExtId());
                    categoryNames.add(dto);

                }
            }
            return MethodResponse.success(categoryNames);
        } catch (Exception e) {
            getLogger().error("[ Error when getting categories by language ]", e);
            return MethodResponse.error(DaoError.CATEGORY_GET_BY_LANGUAGE);
        }

    }

    private MethodResponse<String> getNameByLanguage(List<CategoryName> categories, String languageId) {
        for (CategoryName cat : categories) {
            if (cat.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                return MethodResponse.success(cat.getName());
            }
        }
        return MethodResponse.error(DaoError.CATEGORY_NAME_BY_LANGUAGE);
    }

    @Override
    public MethodResponse<String> create(CategoryDto dto) {

        MethodResponse<Language> languageEntity = languageDao.getByExtId(dto.getDefaultLanguage());
        if (languageEntity.hasErrors) {
            return MethodResponse.error(languageEntity.getErrorCode());
        }

        try {
            Category category = new Category();
            category.setDescription(dto.getDescription());
            category.setDefaultName(dto.getDefaultName());

            List<CategoryName> ceteogyNames = new ArrayList<>();
            CategoryName categoryname = new CategoryName();
            categoryname.setLanguage(languageEntity.getData());
            categoryname.setName(dto.getDefaultName());
            categoryname.setCategory(category);
            ceteogyNames.add(categoryname);

            category.setNames(ceteogyNames);

            return super.create(category);
        } catch (Exception e) {
            getLogger().error("[ Error when creating category ]", e);
            return MethodResponse.error(DaoError.CATEGORY_CREATE);
        }
    }

    @Override
    public MethodResponse<String> updateCategoryName(NameDto name, String categoryNameId) {
        MethodResponse<CategoryName> categoryName = categoryNameDao.getByExtId(categoryNameId);
        if (categoryName.hasErrors) {
            return MethodResponse.error(categoryName.getErrorCode());
        }

        //TODO FIX THIS WITH LANGUAGE SUPPORT!
        /*MethodResponse updatedCategoryName = NameMapper.getInstance().updateEntityFromDto(categoryName, name);
         if (updatedCategoryName.hasErrors) {
         return MethodResponse.error(updatedCategoryName.getErrorCode());
         }*/
        return categoryNameDao.update(categoryName.getData());
    }

}
