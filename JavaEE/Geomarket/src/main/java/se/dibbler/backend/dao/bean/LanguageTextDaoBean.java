/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.LanguageTextDao;
import se.dibbler.backend.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.entity.LanguageText;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.Response;

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
