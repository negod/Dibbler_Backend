/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.constants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.entity.PublishedEvent;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlEnum
public enum IndexingClasses {

    @XmlEnumValue("Location")
    LOCATION(Location.class),
    @XmlEnumValue("PublishedEvent")
    PUBLISHED_EVENT(PublishedEvent.class);

    private final Class clazz;

    private IndexingClasses(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClassToIndex() {
        return clazz;
    }

    public static IndexingClasses fromValue(String v) {
        for (IndexingClasses c : IndexingClasses.values()) {
            if (c.name().toLowerCase().equals(v.toLowerCase())) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
