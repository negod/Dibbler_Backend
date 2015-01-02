/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import se.dibbler.backend.geomarket.constants.DibblerNamedQueries;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.EVENTTEXT_FINDBY_LANGUAGE_EXTID, query = "SELECT c FROM EventText c where c.language.extId =:languageExtId")})
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
