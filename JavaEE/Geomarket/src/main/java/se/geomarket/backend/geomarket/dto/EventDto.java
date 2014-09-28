/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import java.util.Date;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakim
 */
public class EventDto extends BaseDto {

    String companyId;
    String categoryId;
    String eventTypeId;
    String languageId;
    String EventHeader;
    String eventTextBody;
    Date startDate;
    Date endDate;
    Integer maxredeem;

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
        return EventHeader;
    }

    public void setEventHeader(String EventHeader) {
        this.EventHeader = EventHeader;
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

    public Integer getMaxredeem() {
        return maxredeem;
    }

    public void setMaxredeem(Integer maxredeem) {
        this.maxredeem = maxredeem;
    }

}
