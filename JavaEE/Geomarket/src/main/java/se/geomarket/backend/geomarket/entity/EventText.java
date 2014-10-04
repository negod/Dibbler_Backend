/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
@Entity
public class EventText extends BaseEntity {

    @NotNull(message = "eventText.heading cannot be null")
    @Column
    private String heading;
    @NotNull(message = "eventText.body cannot be null")
    @Column
    private String body;
    @NotNull(message = "eventText.language cannot be null, must be an existing language")
    @OneToOne(fetch = FetchType.LAZY)
    Language language;
    @OneToOne(fetch = FetchType.LAZY)
    private Event event;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
