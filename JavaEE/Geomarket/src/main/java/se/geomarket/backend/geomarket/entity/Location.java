/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import com.vividsolutions.jts.geom.Point;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 */

@Entity
public class Location extends BaseEntity{
    
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;
    
    @Column
    private String name;
    
    @OneToOne
    Company company;
    
    
    
}
