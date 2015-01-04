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
import se.dibbler.backend.dto.summary.CompanySummaryDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A definition of a company")
@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyDto extends CompanySummaryDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "Organization number of the company", required = true)
    private String orgNr;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "If the user that wants to follow the company has to meet certain criterias", required = false)
    private String followerClaim;

    @XmlElement(type = LocationDto.class, required = true)
    @ApiModelProperty(value = "The exact location of the company", required = true)
    private LocationDto location;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The picture for the company in Base64 format", required = true)
    private String picture;

    public String getOrgNr() {
        return orgNr;
    }

    public void setOrgNr(String idNr) {
        this.orgNr = idNr;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getFollowerClaim() {
        return followerClaim;
    }

    public void setFollowerClaim(String followerClaim) {
        this.followerClaim = followerClaim;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
