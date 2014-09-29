/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDtoEmpty;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A fixed position with longitude and latitude")
@XmlRootElement(name = "point")
@XmlAccessorType(XmlAccessType.FIELD)
public class PointDto extends BaseDtoEmpty {

    @XmlElement(type = Double.class, required = true)
    @ApiModelProperty(value = "The longitude of the position", required = true)
    private Double longitude;

    @XmlElement(type = Double.class, required = true)
    @ApiModelProperty(value = "The latitude of the position", required = true)
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
