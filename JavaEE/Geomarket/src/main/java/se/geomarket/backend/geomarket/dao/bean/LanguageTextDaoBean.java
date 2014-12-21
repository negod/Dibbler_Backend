/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.LanguageTextDao;
import se.geomarket.backend.geomarket.dto.languagesupport.LanguageTextDto;
import se.geomarket.backend.geomarket.entity.LanguageText;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class LanguageTextDaoBean extends BaseDaoBean<LanguageText, LanguageTextDto> implements LanguageTextDao<LanguageText, LanguageTextDto> {

    public LanguageTextDaoBean() {
        super(LanguageText.class);
    }

    @Override
    public Response<String> create(LanguageTextDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
