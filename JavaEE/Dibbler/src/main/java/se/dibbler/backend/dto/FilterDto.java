/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A userdefined filter to remove selected companies from the list of events")
@XmlRootElement(name = "filter")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilterDto extends BaseDto {

    @XmlElement(type = Boolean.class, required = true)
    @ApiModelProperty(value = "A flag to see if the filter is active", required = true)
    Boolean active;

    @XmlElement(type = UsersDto.class, required = true)
    @ApiModelProperty(value = "The user who has defined the filter", required = true)
    UsersDto user;

    @XmlElement(type = CompanyDto.class, required = true)
    @ApiModelProperty(value = "The company that the filter removes", required = true)
    CompanyDto company;

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UsersDto getUser() {
        return user;
    }

    public void setUser(UsersDto user) {
        this.user = user;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

}
