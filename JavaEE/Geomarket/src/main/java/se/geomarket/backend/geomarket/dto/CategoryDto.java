/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import se.geomarket.backend.geomarket.dto.languagesupport.LanguageTextDto;
import com.wordnik.swagger.annotations.ApiModel;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim
 */
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "A category for different types of events that are created and published")
public class CategoryDto extends LanguageTextDto {

    public CategoryDto() {
    }

    public CategoryDto(String language, String value, String description) {
        super(language, value, description);
    }
}
