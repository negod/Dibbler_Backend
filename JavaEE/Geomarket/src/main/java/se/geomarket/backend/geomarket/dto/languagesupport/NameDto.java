/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto.languagesupport;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDtoEmpty;

/**
 *
 * @author Joakim
 */
@ApiModel(value = "A base type for language support which enables different types to have the same name defines in different languages")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "name")
public class NameDto extends BaseDtoEmpty {

    @ApiModelProperty(value = "The id of the name", required = false)
    @XmlElement(required = false)
    private String id;
    @XmlElement(required = true)
    @ApiModelProperty(value = "The id of the language", required = true)
    private String languageId;
    @XmlElement(required = true)
    @ApiModelProperty(value = "The name in a different language than the basename [ See class basename for more info ]", required = true)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

}
