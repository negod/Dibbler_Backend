/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import se.geomarket.backend.geomarket.entity.superclass.BaseName;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class EventType extends BaseName {

    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL)
    List<EventTypeName> names;

    @Transient
    private Language language;

    public List<EventTypeName> getNames() {
        return names;
    }

    public void setNames(List<EventTypeName> names) {
        this.names = names;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        List<EventTypeName> newNamesList = new ArrayList<>();
        EventTypeName eventTypeName = new EventTypeName();
        eventTypeName.setEventType(this);
        eventTypeName.setLanguage(this.language);
        eventTypeName.setName(this.getDefaultName());
    }

}
