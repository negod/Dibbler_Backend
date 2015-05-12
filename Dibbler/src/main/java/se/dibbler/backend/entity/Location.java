/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
/*@Spatial(spatialMode = SpatialMode.GRID)*/
@Entity
/*@Indexed*/
public class Location extends BaseEntity {

    //@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @NotNull(message = "cannot be null")
    @Latitude
    Double latitude;

    //@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @NotNull(message = "cannot be null")
    @Longitude
    Double longitude;

    @Column
    @NotNull(message = "location name cannot be null")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void inactivate() {
    }

}
