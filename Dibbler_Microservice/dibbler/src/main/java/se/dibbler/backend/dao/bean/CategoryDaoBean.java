/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.constants.TextType;
import se.dibbler.backend.dao.CategoryDao;
import se.dibbler.backend.dao.CategoryTextDao;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dto.CategoryDto;
import se.dibbler.backend.dto.summary.NameSummaryDto;
import se.dibbler.backend.entity.Category;
import se.dibbler.backend.entity.CategoryText;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.mapper.summary.CategorySummaryMapper;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDaoBean;
import se.dibbler.generic.error.GenericError;

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
        super(Category.class, CategoryDto.class);
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
    public Response<List<NameSummaryDto>> getCategoriesByLanguage(String languageId) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("languageExtId", languageId);
            Response<List<CategoryText>> categoryTexts = languageText.getListByNamedQuery(DibblerNamedQueries.CATEGORY_FINDBY_LANGUAGE_EXTID, params);

            if (categoryTexts.hasErrors) {
                return Response.error(categoryTexts.getError());
            }

            return CategorySummaryMapper.getInstance().mapToDtoList(categoryTexts.getData());

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
            category.setDefaultName(dto.getDefaultName());

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

    @Override
    public Response<String> update(CategoryDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
