/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.CategoryTextDao;
import se.dibbler.backend.dto.languagesupport.CategoryTextDto;
import se.dibbler.backend.entity.CategoryText;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

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
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<String> updateCategoryTypeNameByEventTextId(String name, String categoryTypeTextId) {
        Response<CategoryText> evetTypeText = super.getByExtId(categoryTypeTextId);

        if (evetTypeText.hasErrors) {
            return Response.error(evetTypeText.getError());
        }

        evetTypeText.getData().setValue(name);

        return Response.success(evetTypeText.getData().getExtId());
    }

    @Override
    public Response<String> update(CategoryTextDto dto, String extId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
