/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.dao.bean;

import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoImpl;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class LanguageDaoBean extends BaseDaoImpl implements LanguageDao{
    
    public LanguageDaoBean() {
        super(Language.class);
    }
}
