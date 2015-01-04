/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;
import org.hibernate.search.annotations.Spatial;
import org.hibernate.search.annotations.SpatialMode;
import org.hibernate.search.annotations.Store;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
@Spatial(spatialMode = SpatialMode.GRID)
@Entity
@Indexed
public class Location extends BaseEntity {

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @NotNull(message = "cannot be null")
    @Latitude
    Double latitude;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @NotNull(message = "cannot be null")
    @Longitude
    Double longitude;

    @OneToOne(fetch = FetchType.EAGER)
    Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
