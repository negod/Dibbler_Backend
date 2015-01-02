/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.languagesupport.CategoryTextDto;
import se.dibbler.backend.entity.CategoryText;
import se.dibbler.backend.generics.BaseMapper;

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
