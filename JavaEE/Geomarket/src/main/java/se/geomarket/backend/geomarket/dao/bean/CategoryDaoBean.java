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
import se.geomarket.backend.geomarket.generics.DaoResponse;
import se.geomarket.backend.geomarket.mapper.NameMapper;

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
    public String addLanguage(String categoryId, String name, String language) {

        Language languageEntity = (Language) languageDao.getByExtId(language);
        Category category = (Category) super.getByExtId(categoryId);

        //TODO check that language does not exist in category        
        CategoryName categoryname = new CategoryName();
        categoryname.setLanguage(languageEntity);
        categoryname.setName(name);
        categoryname.setCategory(category);
        category.getNames().add(categoryname);

        return category.getExtId();
    }

    @Override
    public List getCategoriesByLanguage(String languageId) {
        List<Category> allCategories = super.getAll();
        List<NameSummaryDto> categoryNames = new ArrayList<>();

        for (Category category : allCategories) {
            String name = getNameByLanguage(category.getNames(), languageId);
            NameSummaryDto dto = new NameSummaryDto();
            if (name.isEmpty()) {
                dto.setName(category.getDefaultName());
            } else {
                dto.setName(name);
            }
            dto.setId(category.getExtId());
            categoryNames.add(dto);
        }

        return categoryNames;
    }

    private String getNameByLanguage(List<CategoryName> categories, String languageId) {
        for (CategoryName cat : categories) {
            if (cat.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                return cat.getName();
            }
        }
        return "";
    }

    @Override
    public DaoResponse create(CategoryDto dto) {
        Language languageEntity = (Language) languageDao.getByExtId(dto.getDefaultLanguage());

        Category category = new Category();
        category.setDescription(dto.getDescription());
        category.setDefaultName(dto.getDefaultName());

        List<CategoryName> ceteogyNames = new ArrayList<>();
        CategoryName categoryname = new CategoryName();
        categoryname.setLanguage(languageEntity);
        categoryname.setName(dto.getDefaultName());
        categoryname.setCategory(category);
        ceteogyNames.add(categoryname);

        category.setNames(ceteogyNames);

        return super.create(category);
    }

    @Override
    public String updateCategoryName(NameDto name, String categoryNameId) {
        CategoryName categoryName = (CategoryName) categoryNameDao.getByExtId(categoryNameId);
        NameMapper.getInstance().updateEntityFromDto(categoryName, name);
        categoryNameDao.update(categoryName);
        return categoryName.getExtId();
    }

}
