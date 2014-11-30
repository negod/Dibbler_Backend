/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class CategoryMapper extends BaseMapper<CategoryDto, Category> {

    private static final CategoryMapper INSTANCE = new CategoryMapper();

    public static CategoryMapper getInstance() {
        return INSTANCE;
    }

    public CategoryMapper() {
        super(CategoryDto.class, Category.class);
    }

}
