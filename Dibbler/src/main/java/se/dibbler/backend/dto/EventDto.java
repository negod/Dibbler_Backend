/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.constants.EventRecipientType;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDto extends BaseDto {

    @XmlElement(type = String.class)
    String companyId;

    @XmlElement(type = String.class)
    String categoryId;

    @XmlElement(type = String.class)
    String eventTypeId;

    @XmlElement(type = EventTextDto.class)
    List<EventTextDto> eventTexts;

    @XmlElement(type = Date.class)
    Date startDate;

    @XmlElement(type = Date.class)
    Date endDate;

    @XmlElement(type = Integer.class, defaultValue = "0")
    Integer maxRedeem;

    @XmlElement(type = String.class)
    String picture;

    @XmlElement(type = EventRecipientType.class)
    private EventRecipientType recipientType;

    @XmlElement(type = Boolean.class, nillable = false, defaultValue = "false")
    private Boolean generateFollowUp;

    @XmlElement(type = Boolean.class, nillable = false, defaultValue = "false")
    private Boolean publishAtLocation;

    @XmlElement(type = Boolean.class, nillable = false, defaultValue = "false")
    private Boolean publishAtBranch;

    @XmlElement(type = Boolean.class, nillable = false, defaultValue = "false")
    private Boolean publishAtCompany;

    @XmlElement(type = String.class, nillable = true)
    List<String> locationIds;

    public List<EventTextDto> getEventTexts() {
        return eventTexts;
    }

    public void setEventTexts(List<EventTextDto> eventTexts) {
        this.eventTexts = eventTexts;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxRedeem() {
        return maxRedeem;
    }

    public void setMaxRedeem(Integer maxredeem) {
        this.maxRedeem = maxredeem;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public EventRecipientType getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(EventRecipientType recipientType) {
        this.recipientType = recipientType;
    }

    public Boolean getGenerateFollowUp() {
        return generateFollowUp;
    }

    public void setGenerateFollowUp(Boolean generateFollowUp) {
        this.generateFollowUp = generateFollowUp;
    }

    public Boolean getPublishAtLocation() {
        return publishAtLocation;
    }

    public void setPublishAtLocation(Boolean publishAtLocation) {
        this.publishAtLocation = publishAtLocation;
    }

    public Boolean getPublishAtBranch() {
        return publishAtBranch;
    }

    public void setPublishAtBranch(Boolean publishAtBranch) {
        this.publishAtBranch = publishAtBranch;
    }

    public Boolean getPublishAtCompany() {
        return publishAtCompany;
    }

    public void setPublishAtCompany(Boolean publishAtCompany) {
        this.publishAtCompany = publishAtCompany;
    }

    public List<String> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<String> locationIds) {
        this.locationIds = locationIds;
    }

}
