/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.CategoryNameDto;
import se.geomarket.backend.geomarket.entity.CategoryName;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CategoryNameMapper extends BaseMapper<CategoryNameDto, CategoryName> {

    private static final CategoryNameMapper INSTANCE = new CategoryNameMapper();

    public static CategoryNameMapper getInstance() {
        return INSTANCE;
    }

    public CategoryNameMapper() {
        super(CategoryNameDto.class, CategoryName.class);
    }

}
