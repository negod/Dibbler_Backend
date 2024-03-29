/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.constants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlEnum()
public enum DaoTypes {

    @XmlEnumValue("CATEGORY")
    CATEGORY,
    @XmlEnumValue("CATEGORY_NAME")
    CATEGORY_NAME,
    @XmlEnumValue("COMPANY")
    COMPANY,
    @XmlEnumValue("COMPANY_USERS")
    COMPANY_USERS,
    @XmlEnumValue("EVENT")
    EVENT,
    @XmlEnumValue("EVENT_TEXT")
    FILTER,
    @XmlEnumValue("LANGUAGE")
    LANGUAGE,
    @XmlEnumValue("MOVMENT")
    MOVMENT,
    @XmlEnumValue("ROLES")
    ROLES,
    @XmlEnumValue("USERS")
    USERS,
    @XmlEnumValue("EVENTTYPE")
    EVENTTYPE,
    @XmlEnumValue("EVENTTEXT")
    EVENTTEXT;

}
