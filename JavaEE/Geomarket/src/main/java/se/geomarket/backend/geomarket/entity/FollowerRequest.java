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
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
@Entity
public class FollowerRequest extends BaseEntity {

    @Column
    private Boolean mandatory;
    @Column
    private String defaultName;
    @Column
    private String description;

    @OneToMany(mappedBy = "followerRequest")
    List<FollowerRequest> followerRequest;

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FollowerRequest> getFollowerRequest() {
        return followerRequest;
    }

    public void setFollowerRequest(List<FollowerRequest> followerRequest) {
        this.followerRequest = followerRequest;
    }

}
