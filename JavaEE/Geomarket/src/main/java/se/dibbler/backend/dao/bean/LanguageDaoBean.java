/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dto.LanguageDto;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.LanguageMapper;

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

    @Override
    public Response<String> update(LanguageDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }
}
