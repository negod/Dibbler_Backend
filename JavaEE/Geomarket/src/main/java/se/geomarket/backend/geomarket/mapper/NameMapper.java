/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.NameDto;
import se.geomarket.backend.geomarket.entity.Name;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
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
        Mapper mapper = new DozerBeanMapper();
        Name destObject = mapper.map(dto, Name.class);
        return destObject;
    }

    @Override
    public NameDto mapFromEntityToDto(Name entity) {
        Mapper mapper = new DozerBeanMapper();
        NameDto destObject = mapper.map(entity, NameDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Name entity, NameDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
