/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.constants.DibblerNamedQueries;
import se.geomarket.backend.geomarket.constants.TextType;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.CategoryTextDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.languagesupport.CategoryTextDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.CategoryText;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;
import se.geomarket.backend.geomarket.mapper.CategoryTextMapper;

/**
 *
 * @author Joakim
 */
@Stateless
public class CategoryDaoBean extends BaseDaoBean<Category, CategoryDto> implements CategoryDao<Category, CategoryDto> {

    @EJB
    LanguageDao languageDao;
    @EJB
    CategoryTextDao languageText;

    public CategoryDaoBean() {
        super(Category.class);
    }

    @Override
    public Response<String> addLanguage(String categoryId, String name, String language) {

        Response<Language> languageEntity = languageDao.getByExtId(language);
        if (languageEntity.hasErrors) {
            return Response.error(languageEntity.getError());
        }

        Response<Category> category = super.getByExtId(categoryId);
        if (category.hasErrors) {
            return Response.error(category.getError());
        }

        try {
            //TODO check that language does not exist in category    
            CategoryText categoryname = new CategoryText();
            categoryname.setLanguage(languageEntity.getData());
            categoryname.setValue(name);
            categoryname.setCategory(category.getData());
            categoryname.setTextType(TextType.NAME);

            return languageText.create(categoryname);
        } catch (Exception e) {
            getLogger().error(DaoError.CATEGORY_ADD_LANGUAGE.getErrorText(), e);
            return Response.error(DaoError.CATEGORY_ADD_LANGUAGE);
        }
    }

    @Override
    public Response<List<CategoryTextDto>> getCategoriesByLanguage(String languageId) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("languageExtId", languageId);
            Response<List<CategoryText>> categoryTexts = languageText.getListByNamedQuery(DibblerNamedQueries.CATEGORY_FINDBY_LANGUAGE_EXTID, params);

            if (categoryTexts.hasErrors) {
                return Response.error(categoryTexts.getError());
            }

            return CategoryTextMapper.getInstance().mapToDtoList(categoryTexts.getData());

        } catch (Exception e) {
            getLogger().error(DaoError.CATEGORY_GET_BY_LANGUAGE.getErrorText(), e.getMessage());
            return Response.error(DaoError.CATEGORY_GET_BY_LANGUAGE);
        }

    }

    @Override
    public Response<String> create(CategoryDto dto) {

        Response<Language> languageEntity = languageDao.getByExtId(dto.getDefaultLanguage());
        if (languageEntity.hasErrors) {
            return Response.error(languageEntity.getError());
        }

        try {
            Category category = new Category();
            category.setDescription(dto.getDescription());
            category.setDefaultLanguage(languageEntity.getData());

            List<CategoryText> cetegoryNames = new ArrayList<>();
            CategoryText categoryName = new CategoryText();
            categoryName.setLanguage(languageEntity.getData());
            categoryName.setValue(dto.getDefaultName());
            categoryName.setCategory(category);
            categoryName.setTextType(TextType.NAME);
            cetegoryNames.add(categoryName);
            category.setCategoryTexts(cetegoryNames);

            return super.create(category);
        } catch (Exception e) {
            getLogger().error(DaoError.CATEGORY_CREATE.getErrorText(), e.getMessage());
            return Response.error(DaoError.CATEGORY_CREATE);
        }
    }

    @Override
    public Response<String> updateCategoryDescription(String description, String categoryId) {
        Response<Category> category = super.getByExtId(categoryId);
        if (category.hasErrors) {
            return Response.error(category.getError());
        }

        category.getData().setDescription(description);

        return Response.success(category.getData().getExtId());
    }

}
