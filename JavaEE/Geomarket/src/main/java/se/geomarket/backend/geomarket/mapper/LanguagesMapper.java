/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.LanguageDto;
import se.geomarket.backend.geomarket.entity.Lang;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class LanguagesMapper extends BaseMapper<LanguageDto, Lang> {

    private static final LanguagesMapper INSTANCE = new LanguagesMapper();

    private LanguagesMapper() {
    }

    public static LanguagesMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Lang mapFromDtoToEntity(LanguageDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Lang destObject = mapper.map(dto, Lang.class);
        return destObject;
    }

    @Override
    public LanguageDto mapFromEntityToDto(Lang entity) {
        Mapper mapper = new DozerBeanMapper();
        LanguageDto destObject = mapper.map(entity, LanguageDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Lang entity, LanguageDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
