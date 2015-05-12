/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import se.dibbler.generic.entity.BaseEntity;
import se.dibbler.generic.user.User;

/**
 *
 * @author Joakim
 */
@Entity
public class Session extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Column
    private String deviceId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void inactivate() {
    }

}
