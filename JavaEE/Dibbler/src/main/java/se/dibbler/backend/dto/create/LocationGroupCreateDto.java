/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.create;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "locationGroup")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "A location group where locations attached to a company can be ordered")
public class LocationGroupCreateDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The name of the LocationGroup", required = true)
    private String name;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "A list of locationIds to add to the locationGroup", required = true)
    private List<String> locations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

}
