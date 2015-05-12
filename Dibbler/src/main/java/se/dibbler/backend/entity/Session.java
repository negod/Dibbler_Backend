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
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
@Entity
public class Session extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Users user;
    @Column
    private String deviceId;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
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
