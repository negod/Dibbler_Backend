/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import se.geomarket.backend.geomarket.entity.superclass.Name;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class EventTypeName extends Name {

    @ManyToOne(fetch = FetchType.LAZY)
    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
