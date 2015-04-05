/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.List;
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

    @XmlElement(type = LocationDto.class, required = false)
    @ApiModelProperty(value = "Locations attached the company", required = false)
    private List<LocationDto> locations;

    @XmlElement(type = LocationGroupDto.class, required = false)
    @ApiModelProperty(value = "Locationgroups attacehd to the company", required = false)
    private List<LocationGroupDto> locationGroups;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The picture for the company in Base64 format", required = true)
    private String picture;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The id of the parent company", required = true)
    private String parentCompanyId;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The name of the siteManager", required = true)
    private String siteManager;

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

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

    public List<LocationGroupDto> getLocationGroups() {
        return locationGroups;
    }

    public void setLocationGroups(List<LocationGroupDto> locationGroups) {
        this.locationGroups = locationGroups;
    }

    public String getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(String parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    public String getSiteManager() {
        return siteManager;
    }

    public void setSiteManager(String siteManager) {
        this.siteManager = siteManager;
    }

}
