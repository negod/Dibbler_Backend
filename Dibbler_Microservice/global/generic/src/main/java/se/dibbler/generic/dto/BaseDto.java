/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "base")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseDto extends BaseDtoNoId {

    @XmlElement(type = String.class, required = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
