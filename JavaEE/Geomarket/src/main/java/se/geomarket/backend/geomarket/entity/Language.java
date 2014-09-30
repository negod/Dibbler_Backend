/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Language extends BaseEntity {

    @NotNull
    @Column
    private String shortName;
    @NotNull
    @Column
    private String fullName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.shortName != null ? this.shortName.hashCode() : 0);
        hash = 47 * hash + (this.fullName != null ? this.fullName.hashCode() : 0);
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
        final Language other = (Language) obj;
        if ((this.shortName == null) ? (other.shortName != null) : !this.shortName.equals(other.shortName)) {
            return false;
        }
        if ((this.fullName == null) ? (other.fullName != null) : !this.fullName.equals(other.fullName)) {
            return false;
        }
        return true;
    }

}
