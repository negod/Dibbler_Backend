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

    @Override
    public Name mapFromDtoToEntity(NameDto dto) {
        Name entity = new Name();
        entity.setName(dto.getName());
        entity.setLanguage(LanguageMapper.getInstance().mapFromDtoToEntity(dto.getLanguage()));
        return entity;
    }

    @Override
    public NameDto mapFromEntityToDto(Name entity) {
        NameDto dto = new NameDto();
        dto.setName(entity.getName());
        dto.setLanguage(LanguageMapper.getInstance().mapFromEntityToDto(entity.getLanguage()));
        return dto;
    }

    @Override
    public void updateEntityFromDto(Name entity, NameDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
