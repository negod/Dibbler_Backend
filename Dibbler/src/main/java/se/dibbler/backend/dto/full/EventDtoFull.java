/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.full;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import se.dibbler.backend.constants.EventRecipientType;
import se.dibbler.backend.dto.CategoryDto;
import se.dibbler.backend.dto.EventTextDto;
import se.dibbler.backend.dto.EventTypeDto;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventDtoFull extends BaseDto {

    @XmlElement(type = CategoryDto.class, required = false)
    private CategoryDto category;

    @XmlElement(type = EventTypeDto.class, required = false)
    private EventTypeDto eventType;

    @XmlElement(type = String.class, required = false)
    String companyId;

    @XmlElement(type = String.class, required = false)
    String languageId;

    @XmlElement(type = EventTextDto.class, required = false)
    List<EventTextDto> eventTexts;

    @XmlElement(type = Date.class, required = true)
    Date startDate;

    @XmlElement(type = Date.class, required = true)
    Date endDate;

    @XmlElement(type = Integer.class, defaultValue = "0", required = false)
    Integer maxRedeem;

    @XmlElement(type = String.class, required = false)
    private String imageLargeUrl;

    @XmlElement(type = String.class, required = false)
    private String imageSmallUrl;

    @XmlElement(type = EventRecipientType.class, required = false)
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

    public String getImageLargeUrl() {
        return imageLargeUrl;
    }

    public void setImageLargeUrl(String imageLargeUrl) {
        this.imageLargeUrl = imageLargeUrl;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

    public EventRecipientType getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(EventRecipientType recipientType) {
        this.recipientType = recipientType;
    }

}
