/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.languagesupport.CategoryTextDto;
import se.geomarket.backend.geomarket.entity.CategoryText;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CategoryTextMapper extends BaseMapper<CategoryTextDto, CategoryText> {

    private static final CategoryTextMapper INSTANCE = new CategoryTextMapper();

    public static CategoryTextMapper getInstance() {
        return INSTANCE;
    }

    public CategoryTextMapper() {
        super(CategoryTextDto.class, CategoryText.class);
    }

}
