/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import se.geomarket.backend.geomarket.entity.superclass.BaseName;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class EventType extends BaseName {

    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL)
    List<EventTypeName> names;

    public List<EventTypeName> getNames() {
        return names;
    }

    public void setNames(List<EventTypeName> names) {
        this.names = names;
    }

}
