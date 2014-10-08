/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto.summary;

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

@ApiModel(value = "A generic nametype for language support")
@XmlRootElement(name = "nameType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameSummaryDto extends BaseDtoEmpty{
    
    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The id of the type", required = false)
    String id;
    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The actual name", required = false)
    String name;

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
    
}
