/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity.id;

import java.io.Serializable;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NameId implements Serializable {

    private Long id;
    private Long language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLanguage() {
        return language;
    }

    public void setLanguage(Long language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.language != null ? this.language.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NameId other = (NameId) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.language != other.language && (this.language == null || !this.language.equals(other.language))) {
            return false;
        }
        return true;
    }

}
