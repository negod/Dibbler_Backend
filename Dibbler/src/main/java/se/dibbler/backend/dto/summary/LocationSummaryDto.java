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
import se.dibbler.backend.generics.BaseDtoNoId;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationSummaryDto extends BaseDtoNoId {

    @XmlElement(type = Double.class, required = true)
    Double latitude;

    @XmlElement(type = Double.class, required = true)
    Double longitude;

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

}
