/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.LanguageDto;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class LanguageMapper extends BaseMapper<LanguageDto, Language> {

    private static final LanguageMapper INSTANCE = new LanguageMapper();

    private LanguageMapper() {
    }

    public static LanguageMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Language mapFromDtoToEntity(LanguageDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Language destObject = mapper.map(dto, Language.class);
        return destObject;
    }

    @Override
    public LanguageDto mapFromEntityToDto(Language entity) {
        Mapper mapper = new DozerBeanMapper();
        LanguageDto destObject = mapper.map(entity, LanguageDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Language entity, LanguageDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
