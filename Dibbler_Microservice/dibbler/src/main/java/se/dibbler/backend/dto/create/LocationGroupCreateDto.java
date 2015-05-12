/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.create;

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
public class LocationGroupCreateDto {

    @XmlElement(type = String.class, required = true)
    private String name;

    @XmlElement(type = String.class, required = true)
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
