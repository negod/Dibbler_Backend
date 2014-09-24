/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.superclass.BaseName;
import se.geomarket.backend.geomarket.entity.superclass.Name;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class CategoryMapper extends BaseMapper<CategoryDto, Category> {

    private static final CategoryMapper INSTANCE = new CategoryMapper();

    private CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Category mapFromDtoToEntity(CategoryDto dto) {
        return (Category) BaseNameMapper.getInstance().mapFromDtoToEntity(dto);
    }

    @Override
    public CategoryDto mapFromEntityToDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getExtId());
        dto.setDefaultName(entity.getDefaultName());
        dto.setDescription(entity.getDescription());
        for (Name name : entity.getNames()) {
            dto.getNames().add(NameMapper.getInstance().mapFromEntityToDto(name));
        }
        return dto;
    }

    @Override
    public void updateEntityFromDto(Category entity, CategoryDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
