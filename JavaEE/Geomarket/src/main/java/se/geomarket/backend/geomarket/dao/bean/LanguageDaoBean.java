/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.languagesupport.LanguageDto;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;
import se.geomarket.backend.geomarket.mapper.LanguageMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class LanguageDaoBean extends BaseDaoBean<Language, LanguageDto> implements LanguageDao<Language, LanguageDto> {

    public LanguageDaoBean() {
        super(Language.class);
    }

    @Override
    public MethodResponse create(LanguageDto dto) {
        MethodResponse<Language> entity = LanguageMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return MethodResponse.error(entity.getErrorCode());
        }
        return super.create(entity.getData());
    }
}
