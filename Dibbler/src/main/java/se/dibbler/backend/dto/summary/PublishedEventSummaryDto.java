/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.summary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "publishedEvent")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "id",
    "companyId",
    "languageId",
    "eventTypeId",
    "categoryId",
    "eventId",
    "heading",
    "body",
    "starts",
    "expires",
    "latitude",
    "longitude"})
public class PublishedEventSummaryDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    private String companyId;

    @XmlElement(type = String.class, required = true)
    private String languageId;

    @XmlElement(type = String.class, required = true)
    private String eventTypeId;

    @XmlElement(type = String.class, required = true)
    private String categoryId;

    @XmlElement(type = String.class, required = true, nillable = false)
    private String eventId;

    @XmlElement(type = Long.class, required = true)
    private Long expires;

    @XmlElement(type = Long.class, required = true)
    private Long starts;

    @XmlElement(type = String.class, required = true)
    private String heading;

    @XmlElement(type = String.class, required = true)
    private String body;

    @XmlElement(type = Double.class, required = true)
    private Double latitude;

    @XmlElement(type = Double.class, required = true)
    private Double longitude;

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

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public Long getStarts() {
        return starts;
    }

    public void setStarts(Long starts) {
        this.starts = starts;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
