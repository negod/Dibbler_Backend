/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Defines an event that can be published to Dibblers users")
@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "Id of the company that publishes the event", required = true)
    String companyId;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "Id of the events category", required = true)
    String categoryId;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "Id of the events type", required = true)
    String eventTypeId;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default language of the event [ Will be refactored to support several languages ]", required = true)
    String languageId;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "A short header to the event [ Will be refactored to support several languages ]", required = true)
    String EventHeader;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "Informative text about the event [ Will be refactored to support several languages ]", required = true)
    String eventTextBody;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "The startdate of the event", required = true)
    Date startDate;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "The enddate of the event", required = true)
    Date endDate;

    @XmlElement(type = Integer.class, defaultValue = "0", required = false)
    @ApiModelProperty(value = "The number of times this event can be used", required = false)
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
