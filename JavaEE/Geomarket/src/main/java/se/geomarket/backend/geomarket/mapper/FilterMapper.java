/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import se.geomarket.backend.geomarket.dto.FilterDto;
import se.geomarket.backend.geomarket.entity.Filter;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class FilterMapper extends BaseMapper<FilterDto, Filter> {

    private static final FilterMapper INSTANCE = new FilterMapper();

    public static FilterMapper getInstance() {
        return INSTANCE;
    }

    public FilterMapper() {
        super(FilterDto.class, Filter.class);
    }

}
