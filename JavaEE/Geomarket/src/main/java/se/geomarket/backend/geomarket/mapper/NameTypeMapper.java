/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.NameTypeDto;
import se.geomarket.backend.geomarket.entity.NameType;
import se.geomarket.backend.geomarket.entity.User;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NameTypeMapper extends BaseMapper<NameTypeDto, NameType> {

    private static final NameTypeMapper INSTANCE = new NameTypeMapper();

    private NameTypeMapper() {
    }

    public static NameTypeMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public NameType mapFromDtoToEntity(NameTypeDto dto) {
        Mapper mapper = new DozerBeanMapper();
        NameType destObject = mapper.map(dto, NameType.class);
        return destObject;
    }

    @Override
    public NameTypeDto mapFromEntityToDto(NameType entity) {
        Mapper mapper = new DozerBeanMapper();
        NameTypeDto destObject = mapper.map(entity, NameTypeDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(NameType entity, NameTypeDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
