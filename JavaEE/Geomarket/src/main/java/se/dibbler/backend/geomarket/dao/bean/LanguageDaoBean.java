/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.geomarket.dao.LanguageDao;
import se.dibbler.backend.geomarket.dto.LanguageDto;
import se.dibbler.backend.geomarket.entity.Language;
import se.dibbler.backend.geomarket.generics.BaseDaoBean;
import se.dibbler.backend.geomarket.generics.Response;
import se.dibbler.backend.geomarket.mapper.LanguageMapper;

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
    public Response create(LanguageDto dto) {
        Response<Language> entity = LanguageMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return super.create(entity.getData());
    }
}
