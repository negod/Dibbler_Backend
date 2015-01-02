/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dto.languagesupport;

import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlElement;
import se.dibbler.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class BaseTypeDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default name of the type", required = true)
    private String defaultName;
    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default description of the type", required = true)
    private String description;
    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default language ( id ) of the type", required = true)
    private String defaultLanguage;

    public BaseTypeDto() {
    }

    public BaseTypeDto(String defaultLanguage, String defaultName, String description) {
        this.defaultName = defaultName;
        this.description = description;
        this.defaultLanguage = defaultLanguage;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

}
