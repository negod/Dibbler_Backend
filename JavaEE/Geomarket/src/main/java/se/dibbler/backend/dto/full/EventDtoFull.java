/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.full;

import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import se.dibbler.backend.constants.EventRecipientType;
import se.dibbler.backend.dto.CategoryDto;
import se.dibbler.backend.dto.EventTypeDto;
import se.dibbler.backend.dto.languagesupport.EventTextDto;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventDtoFull extends BaseDto {

    @XmlElement(type = CategoryDto.class, required = false)
    @ApiModelProperty(value = "The events category", required = false, hidden = true)
    private CategoryDto category;

    @XmlElement(type = EventTypeDto.class, required = false)
    @ApiModelProperty(value = "The events type", required = false, hidden = true)
    private EventTypeDto eventType;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "Id of the company that publishes the event", required = false, hidden = true)
    String companyId;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The default language of the event", required = false, hidden = true)
    String languageId;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default event header", required = true)
    String eventHeader;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default eventText Bodys", required = true)
    String eventTextBody;

    @XmlElement(type = EventTextDto.class, required = false)
    @ApiModelProperty(value = "The eventtexts in different languages", required = false, hidden = true)
    List<EventTextDto> eventTexts;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "The startdate of the event", required = true)
    Date startDate;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "The enddate of the event", required = true)
    Date endDate;

    @XmlElement(type = Integer.class, defaultValue = "0", required = false)
    @ApiModelProperty(value = "The number of times this event can be used", required = false)
    Integer maxRedeem;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The picrure for the event in Base64 format", required = false)
    String picture;

    @XmlElement(type = EventRecipientType.class, required = false)
    @ApiModelProperty(value = "The recipient type", required = false)
    private EventRecipientType recipientType;

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public EventTypeDto getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeDto eventType) {
        this.eventType = eventType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public void setEventHeader(String eventHeader) {
        this.eventHeader = eventHeader;
    }

    public String getEventTextBody() {
        return eventTextBody;
    }

    public void setEventTextBody(String eventTextBody) {
        this.eventTextBody = eventTextBody;
    }

    public List<EventTextDto> getEventTexts() {
        return eventTexts;
    }

    public void setEventTexts(List<EventTextDto> eventTexts) {
        this.eventTexts = eventTexts;
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

    public void setMaxRedeem(Integer maxRedeem) {
        this.maxRedeem = maxRedeem;
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
