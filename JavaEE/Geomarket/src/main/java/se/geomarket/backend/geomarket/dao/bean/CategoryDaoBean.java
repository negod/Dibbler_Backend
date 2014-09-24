/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.CategoryName;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.utils.EntityUtils;

/**
 *
 * @author Joakim
 */
@Stateless
public class CategoryDaoBean extends BaseDaoBean implements CategoryDao {

    @EJB
    LanguageDao languageDao;

    public CategoryDaoBean() {
        super(Category.class);
    }

    @Override
    public String addLanguage(String categoryId, String name, String language) {

        Language languageEntity = (Language) languageDao.getByExtId(language);
        Category category = (Category) super.getByExtId(categoryId);

        //TODO check that language does not exist in category
        CategoryName categoryname = (CategoryName) EntityUtils.setEntityCreateData(new CategoryName());
        categoryname.setLanguage(languageEntity);
        categoryname.setName(name);
        categoryname.setCategory(category);
        category.getNames().add(categoryname);

        return category.getExtId();
    }

}
