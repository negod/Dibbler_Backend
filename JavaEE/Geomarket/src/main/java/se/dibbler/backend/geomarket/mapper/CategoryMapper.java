/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.mapper;

import se.dibbler.backend.geomarket.dto.CategoryDto;
import se.dibbler.backend.geomarket.entity.Category;
import se.dibbler.backend.geomarket.generics.BaseMapper;

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
