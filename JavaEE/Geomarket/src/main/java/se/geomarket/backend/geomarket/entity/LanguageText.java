/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import se.geomarket.backend.geomarket.constants.TextType;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@MappedSuperclass
public class LanguageText extends BaseEntity {

    private String value;
    @OneToOne
    private Language language;
    @Enumerated(EnumType.STRING)
    private TextType textType;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TextType getTextType() {
        return textType;
    }

    public void setTextType(TextType textType) {
        this.textType = textType;
    }

}
