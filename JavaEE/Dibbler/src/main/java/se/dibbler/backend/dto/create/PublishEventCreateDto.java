/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.create;

import com.wordnik.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A published event")
@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublishEventCreateDto extends BaseDto {

    private String languageId;
    private String eventId;
    private Date expireDate;
    private Date startDate;
    private List<LocationDto> locations;

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

}
