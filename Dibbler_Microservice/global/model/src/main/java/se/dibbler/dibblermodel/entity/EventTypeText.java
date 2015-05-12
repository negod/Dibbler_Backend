/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.dibblermodel.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import se.dibbler.dibblermodel.constant.DibblerNamedQueries;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.EVENTTYPE_FINDBY_LANGUAGE_EXTID, query = "SELECT c FROM EventTypeText c where c.language.extId =:languageExtId"),})
public class EventTypeText extends LanguageText {

    @ManyToOne(optional = false)
    EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
