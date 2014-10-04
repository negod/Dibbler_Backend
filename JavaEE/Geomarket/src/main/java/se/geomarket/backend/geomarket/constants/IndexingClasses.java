/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.constants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import se.geomarket.backend.geomarket.entity.Location;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlEnum
public enum IndexingClasses {

    @XmlEnumValue("Location")
    LOCATION(Location.class);

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
