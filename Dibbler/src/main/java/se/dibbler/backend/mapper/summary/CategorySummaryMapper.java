/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper.summary;

import se.dibbler.backend.dto.summary.NameSummaryDto;
import se.dibbler.backend.entity.CategoryText;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CategorySummaryMapper extends BaseMapper<NameSummaryDto, CategoryText> {

    private static final CategorySummaryMapper INSTANCE = new CategorySummaryMapper();

    public static CategorySummaryMapper getInstance() {
        return INSTANCE;
    }

    public CategorySummaryMapper() {
        super(NameSummaryDto.class, CategoryText.class);
    }

}
