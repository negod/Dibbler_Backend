/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.create;

import java.util.List;
import se.dibbler.backend.dto.LocationDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class LocationGroupCreateDto {

    private String name;
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
