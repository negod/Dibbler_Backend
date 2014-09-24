/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.languagesupport.NameDto;
import se.geomarket.backend.geomarket.entity.superclass.Name;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class NameMapper extends BaseMapper<NameDto, Name> {

    private static final NameMapper INSTANCE = new NameMapper();

    private NameMapper() {
    }

    public static NameMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Name mapFromDtoToEntity(NameDto dto) {
        Name entity = new Name();
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public NameDto mapFromEntityToDto(Name entity) {
        NameDto dto = new NameDto();
        dto.setId(entity.getExtId());
        dto.setName(entity.getName());
        dto.setLanguageId(entity.getLanguage().getExtId());
        return dto;
    }

    @Override
    public void updateEntityFromDto(Name entity, NameDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
