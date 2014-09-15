/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.ws;

import javax.ejb.EJB;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.LanguageDto;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.LanguageMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class LanguageService extends BaseWs<LanguageDto, Language, LanguageDao>{
    
    @EJB
    LanguageDao languageDao;

    @Override
    public LanguageDao getDao() {
        return languageDao;
    }

    @Override
    public BaseMapper<LanguageDto, Language> getMapper() {
        return LanguageMapper.getInstance();
    }
    
}
