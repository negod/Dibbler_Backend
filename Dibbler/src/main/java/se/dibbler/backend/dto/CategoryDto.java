/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.dto.languagesupport.BaseTypeDto;
import se.dibbler.backend.dto.languagesupport.CategoryTextDto;

/**
 *
 * @author Joakim
 */
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDto extends BaseTypeDto {

    @XmlElement(type = CategoryTextDto.class, required = true, name = "languages")
    List<CategoryTextDto> categoryTexts;

    public CategoryDto(String language, String value, String description) {
        super(language, value, description);
    }

    public CategoryDto() {
    }

    public List<CategoryTextDto> getCategoryTexts() {
        return categoryTexts;
    }

    public void setCategoryTexts(List<CategoryTextDto> categoryTexts) {
        this.categoryTexts = categoryTexts;
    }

}
