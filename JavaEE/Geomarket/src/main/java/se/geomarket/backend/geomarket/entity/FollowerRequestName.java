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
import se.geomarket.backend.geomarket.entity.superclass.Name;

/**
 *
 * @author Joakim
 */
@Entity
public class FollowerRequestName extends Name {

    @ManyToOne(fetch = FetchType.LAZY)
    private FollowerRequest followerRequest;

    public FollowerRequest getFollowerRequest() {
        return followerRequest;
    }

    public void setFollowerRequest(FollowerRequest followerRequest) {
        this.followerRequest = followerRequest;
    }

}
