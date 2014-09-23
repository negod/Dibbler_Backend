/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.languagesupport.BaseNameDto;
import se.geomarket.backend.geomarket.entity.superclass.BaseName;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class BaseNameMapper extends BaseMapper<BaseNameDto, BaseName> {
    
    private static final BaseNameMapper INSTANCE = new BaseNameMapper();
    
    private BaseNameMapper() {
    }
    
    public static BaseNameMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public BaseName mapFromDtoToEntity(BaseNameDto dto) {
        BaseName name = new BaseName();
        name.setDefaultName(dto.getDefaultName());
        name.setDescription(dto.getDescription());
        return name;
    }
    
    @Override
    public BaseNameDto mapFromEntityToDto(BaseName entity) {
        BaseNameDto name = new BaseNameDto();
        name.setDefaultName(entity.getDefaultName());
        name.setDescription(entity.getDescription());
        return name;
    }
    
    @Override
    public void updateEntityFromDto(BaseName entity, BaseNameDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
