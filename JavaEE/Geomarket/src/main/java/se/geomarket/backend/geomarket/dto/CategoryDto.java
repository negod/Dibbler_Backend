/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.dto.languagesupport.BaseTypeDto;
import se.geomarket.backend.geomarket.dto.languagesupport.CategoryTextDto;

/**
 *
 * @author Joakim
 */
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "A category for different types of events that are created and published")
public class CategoryDto extends BaseTypeDto {

    @XmlElement(type = CategoryTextDto.class, required = true, name = "languages")
    @ApiModelProperty(value = "Language support for the Category", required = true)
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
