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

    public static NameMapper getInstance() {
        return INSTANCE;
    }

    public NameMapper() {
        super(NameDto.class, Name.class);
    }

}
