/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import se.dibbler.generic.entity.BaseEntity;
import se.dibbler.generic.user.User;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
public class Setting extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @Column
    private Boolean mapAsDefault;

    @Column
    private Boolean followOnTop;

    @Column
    private Boolean allowPush;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "LANGUAGE_ID", referencedColumnName = "id")
    private Language language;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public void inactivate() {
    }

}
