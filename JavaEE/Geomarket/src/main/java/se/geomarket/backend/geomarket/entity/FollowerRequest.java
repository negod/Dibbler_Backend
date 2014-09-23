/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import se.geomarket.backend.geomarket.entity.superclass.BaseName;

/**
 *
 * @author Joakim
 */
@Entity
public class FollowerRequest extends BaseName {

    @Column
    private Boolean mandatory;

    @OneToMany(mappedBy = "followerRequest")
    List<FollowerRequestName> followerRequestName;

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<FollowerRequestName> getFollowerRequestName() {
        return followerRequestName;
    }

    public void setFollowerRequestName(List<FollowerRequestName> followerRequestName) {
        this.followerRequestName = followerRequestName;
    }

}
