/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.CategoryTextDao;
import se.geomarket.backend.geomarket.dto.languagesupport.CategoryTextDto;
import se.geomarket.backend.geomarket.entity.CategoryText;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */

@Stateless
public class CategoryTextDaoBean extends BaseDaoBean<CategoryText, CategoryTextDto> implements CategoryTextDao<CategoryText, CategoryTextDto> {

    public CategoryTextDaoBean() {
        super(CategoryText.class);
    }

    @Override
    public Response<String> create(CategoryTextDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
