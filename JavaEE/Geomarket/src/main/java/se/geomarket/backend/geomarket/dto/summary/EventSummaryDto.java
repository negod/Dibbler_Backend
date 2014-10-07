/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto.summary;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.dto.PointDto;
import se.geomarket.backend.geomarket.generics.BaseDtoEmpty;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A summary of an event")
@XmlRootElement(name = "eventsummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSummaryDto extends BaseDtoEmpty {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The id of the event", required = true)
    private String id;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The header of the event", required = true)
    private String eventHeader;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The id of the events category", required = true)
    private String categoryId;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "Date when the event expires", required = true)
    private Date expires;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The type of event", required = true)
    private String eventTypeId;

    @XmlElement(type = PointDto.class, required = true)
    @ApiModelProperty(value = "The location of the company where the event takes place", required = true)
    private PointDto location;

    @XmlElement(type = CompanySummaryDto.class, required = true)
    @ApiModelProperty(value = "The company that publishes the event", required = true)
    private CompanySummaryDto company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventHeader() {
        return eventHeader;
    }

    public void setEventHeader(String eventText) {
        this.eventHeader = eventText;
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

    public PointDto getLocation() {
        return location;
    }

    public void setLocation(PointDto location) {
        this.location = location;
    }

    public CompanySummaryDto getCompany() {
        return company;
    }

    public void setCompany(CompanySummaryDto company) {
        this.company = company;
    }

}
