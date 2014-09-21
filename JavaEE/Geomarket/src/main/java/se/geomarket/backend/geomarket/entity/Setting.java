/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
public class Setting extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "id")
    private Users user;

    @Column
    private Boolean mapAsDefault;

    @Column
    private Boolean followOnTop;

    @Column
    private Boolean allowPush;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "LANGUAGE_ID", referencedColumnName = "id")
    private Lang language;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Boolean isMapAsDefault() {
        return mapAsDefault;
    }

    public void setMapAsDefault(Boolean mapAsDefault) {
        this.mapAsDefault = mapAsDefault;
    }

    public Boolean isFollowOnTop() {
        return followOnTop;
    }

    public void setFollowOnTop(Boolean followOnTop) {
        this.followOnTop = followOnTop;
    }

    public Boolean isAllowPush() {
        return allowPush;
    }

    public void setAllowPush(Boolean allowPush) {
        this.allowPush = allowPush;
    }

    public Lang getLanguage() {
        return language;
    }

    public void setLanguage(Lang language) {
        this.language = language;
    }

}
