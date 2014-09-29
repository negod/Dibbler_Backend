/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Defines a role that a user can have")
@XmlRootElement(name = "role")
@XmlAccessorType(XmlAccessType.FIELD)
public class RolesDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The name of the role", required = true)
    private String roleName;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The description of the role", required = true)
    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
