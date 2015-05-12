/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.dibblermodel.entity;

import com.vividsolutions.jts.geom.Point;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Type;
import se.dibbler.generic.entity.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Movement extends BaseEntity {

    @Column
    String sessionId;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public void inactivate() {
    }

}
