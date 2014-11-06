/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto.languagesupport;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A base type for language support")
@XmlRootElement(name = "basename")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseNameDto extends BaseDto {

    public BaseNameDto() {
    }

    public BaseNameDto(String defaultName, String description, String defaultLanguage) {
        this.defaultName = defaultName;
        this.description = description;
        this.defaultLanguage = defaultLanguage;
    }

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default name that will be shown if certain langugaes are not supported (Preferably in english)", required = true)
    private String defaultName;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "A description of the default name", required = true)
    private String description;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The default language", required = false)
    private String defaultLanguage;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "A list of names in different languages that relates to this name", required = false)
    List<NameDto> names = new ArrayList<>();

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

    public List<NameDto> getNames() {
        return names;
    }

    public void setNames(List<NameDto> names) {
        this.names = names;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

}
