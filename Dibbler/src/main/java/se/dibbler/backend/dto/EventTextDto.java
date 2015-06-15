/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import javax.xml.bind.annotation.XmlElement;
import se.dibbler.backend.generics.BaseDtoNoId;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventTextDto extends BaseDtoNoId {

    @XmlElement(type = String.class, required = false)
    String languageId;

    @XmlElement(type = String.class, required = true)
    String header;

    @XmlElement(type = String.class, required = true)
    String description;

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
