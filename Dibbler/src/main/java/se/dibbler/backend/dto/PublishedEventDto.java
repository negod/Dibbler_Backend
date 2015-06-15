/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.dto.summary.CompanySummaryDto;
import se.dibbler.backend.dto.summary.EventTextSummaryDto;
import se.dibbler.backend.dto.summary.LocationSummaryDto;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "publishedEvent")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublishedEventDto extends BaseDto {

    private String language;
    private CompanySummaryDto company;
    private LocationSummaryDto location;
    private EventTextSummaryDto text;
    private String eventType;
    private Long expires;
    private String category;
    private String event;
    private String imageUrl;
    private String imageLargeUrl;
    private String imageSmallUrl;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public CompanySummaryDto getCompany() {
        return company;
    }

    public void setCompany(CompanySummaryDto company) {
        this.company = company;
    }

    public LocationSummaryDto getLocation() {
        return location;
    }

    public void setLocation(LocationSummaryDto location) {
        this.location = location;
    }

    public EventTextSummaryDto getText() {
        return text;
    }

    public void setText(EventTextSummaryDto text) {
        this.text = text;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

}
