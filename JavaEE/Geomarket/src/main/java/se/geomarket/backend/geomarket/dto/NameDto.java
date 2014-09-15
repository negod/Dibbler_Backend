/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.dto;

import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.entity.NameType;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NameDto extends BaseDto{
    
    private String name;
    Language language;
    NameType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public NameType getType() {
        return type;
    }

    public void setType(NameType type) {
        this.type = type;
    }
    
    
    
}
