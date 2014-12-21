/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class EventText extends LanguageText {

    @ManyToOne(optional = false)
    Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
