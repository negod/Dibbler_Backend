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

    public static BaseNameMapper getInstance() {
        return INSTANCE;
    }

    public BaseNameMapper() {
        super(BaseNameDto.class, BaseName.class);
    }

}
