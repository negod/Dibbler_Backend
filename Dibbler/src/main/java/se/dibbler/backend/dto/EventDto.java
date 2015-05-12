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
import se.dibbler.backend.dto.languagesupport.EventTextDto;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDto extends BaseDto {

    @XmlElement(type = String.class, required = false)
    String companyId;

    @XmlElement(type = String.class, required = false)
    String categoryId;

    @XmlElement(type = String.class, required = false)
    String eventTypeId;

    @XmlElement(type = String.class, required = false)
    String languageId;

    @XmlElement(type = String.class, required = true)
    String eventHeader;

    @XmlElement(type = String.class, required = true)
    String eventTextBody;

    @XmlElement(type = EventTextDto.class, required = false)
    List<EventTextDto> eventTexts;

    @XmlElement(type = Date.class, required = true)
    Date startDate;

    @XmlElement(type = Date.class, required = true)
    Date endDate;

    @XmlElement(type = Integer.class, defaultValue = "0", required = false)
    Integer maxRedeem;

    @XmlElement(type = String.class, required = false)
    String picture;

    @XmlElement(type = EventRecipientType.class, required = false)
    private EventRecipientType recipientType;

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

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getEventHeader() {
        return eventHeader;
    }

    public void setEventHeader(String EventHeader) {
        this.eventHeader = EventHeader;
    }

    public String getEventTextBody() {
        return eventTextBody;
    }

    public void setEventTextBody(String eventTextBody) {
        this.eventTextBody = eventTextBody;
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

}
