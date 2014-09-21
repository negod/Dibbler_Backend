/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 */

@Entity
public class FollowerRequestName extends BaseEntity {
    
    @Column
    private String name;
    @OneToOne
    Lang language;
    @ManyToOne(fetch = FetchType.LAZY)
    private FollowerRequest followerRequest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lang getLanguage() {
        return language;
    }

    public void setLanguage(Lang language) {
        this.language = language;
    }

    public FollowerRequest getFollowerRequest() {
        return followerRequest;
    }

    public void setFollowerRequest(FollowerRequest followerRequest) {
        this.followerRequest = followerRequest;
    }
    
    
    
    
    
}
