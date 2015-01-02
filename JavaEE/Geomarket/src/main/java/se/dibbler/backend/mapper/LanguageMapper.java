/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.LanguageDto;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class LanguageMapper extends BaseMapper<LanguageDto, Language> {

    private static final LanguageMapper INSTANCE = new LanguageMapper();

    public static LanguageMapper getInstance() {
        return INSTANCE;
    }

    public LanguageMapper() {
        super(LanguageDto.class, Language.class);
    }

}
