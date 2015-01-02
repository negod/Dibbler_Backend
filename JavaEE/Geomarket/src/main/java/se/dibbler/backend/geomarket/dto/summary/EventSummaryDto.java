/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dto.summary;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A summary of an event")
@XmlRootElement(name = "eventsummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSummaryDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The id of the event", required = true)
    private String id;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The id of the events category", required = true)
    private String categoryId;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "Date when the event expires", required = true)
    private Date expires;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The type of event", required = true)
    private String eventTypeId;

    @XmlElement(type = LocationSummaryDto.class, required = true)
    @ApiModelProperty(value = "The location of the company where the event takes place", required = true)
    private LocationSummaryDto location;

    @XmlElement(type = CompanySummaryDto.class, required = true)
    @ApiModelProperty(value = "The company that publishes the event", required = true)
    private CompanySummaryDto company;

    @XmlElement(type = EventTextSummaryDto.class, required = true)
    @ApiModelProperty(value = "The descriptive text for the event", required = true)
    private EventTextSummaryDto eventText;

    public EventTextSummaryDto getEventText() {
        return eventText;
    }

    public void setEventText(EventTextSummaryDto eventText) {
        this.eventText = eventText;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public LocationSummaryDto getLocation() {
        return location;
    }

    public void setLocation(LocationSummaryDto location) {
        this.location = location;
    }

    public CompanySummaryDto getCompany() {
        return company;
    }

    public void setCompany(CompanySummaryDto company) {
        this.company = company;
    }

}
