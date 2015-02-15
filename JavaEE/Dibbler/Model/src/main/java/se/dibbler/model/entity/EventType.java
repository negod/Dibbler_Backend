/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import se.dibbler.backend.entity.superclass.BaseType;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class EventType extends BaseType {

    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL, orphanRemoval = true)
    List<EventTypeText> eventTexts;

    public List<EventTypeText> getEventTexts() {
        return eventTexts;
    }

    public void setEventTexts(List<EventTypeText> eventTexts) {
        this.eventTexts = eventTexts;
    }

}
