/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.languagesupport.LanguageTextDto;
import se.geomarket.backend.geomarket.entity.LanguageText;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class LanguageTextMapper extends BaseMapper<LanguageTextDto, LanguageText> {

    private static final LanguageTextMapper INSTANCE = new LanguageTextMapper();

    public static LanguageTextMapper getInstance() {
        return INSTANCE;
    }

    public LanguageTextMapper() {
        super(LanguageTextDto.class, LanguageText.class);
    }

}
