/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.create;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.dto.summary.CompanySummaryDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "companyCreate")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyCreateDto extends CompanySummaryDto {

    @XmlElement(type = String.class, required = true)
    private String orgNr;

    @XmlElement(type = String.class, required = false)
    private String followerClaim;

    @XmlElement(type = LocationDto.class, required = true)
    private LocationDto location;

    @XmlElement(type = String.class, required = false)
    private String picture;

    @XmlElement(type = String.class, required = false)
    private String parentCompanyId;

    @XmlElement(type = String.class, required = false)
    private String siteManager;

    public String getOrgNr() {
        return orgNr;
    }

    public void setOrgNr(String orgNr) {
        this.orgNr = orgNr;
    }

    public String getFollowerClaim() {
        return followerClaim;
    }

    public void setFollowerClaim(String followerClaim) {
        this.followerClaim = followerClaim;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
