/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.entity.LanguageText;
import se.dibbler.backend.generics.BaseMapper;

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