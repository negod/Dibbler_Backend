/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.EVENTTEXT_FINDBY_LANGUAGE_EXTID, query = "SELECT c FROM EventText c where c.language.extId =:languageExtId")})
public class EventText extends BaseEntity {

    @Column(nullable = true)
    private String name;

    @Column(nullable = false)
    private String header;

    @Column(nullable = false)
    private String description;

    @OneToOne(optional = false)
    Language language;

    @ManyToOne(optional = false)
    Event event;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void inactivate() {
        this.setActive(false);
    }

}
