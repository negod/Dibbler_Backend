/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String extId;
    /*@JoinColumn(insertable = true, name = "updatedUser")
     @ManyToOne(fetch = FetchType.LAZY)
     private Users updatedUser;
     @JoinColumn(insertable = true, name = "createdUser")
     @ManyToOne(fetch = FetchType.LAZY)
     private Users createdUser;*/
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updatedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    /*public UserEntity getUpdatedUser() {
     return updatedUser;
     }

     public void setUpdatedUser(UserEntity updatedUser) {
     this.updatedUser = updatedUser;
     }

     public UserEntity getCreatedUser() {
     return createdUser;
     }

     public void setCreatedUser(UserEntity createdUser) {
     this.createdUser = createdUser;
     }*/
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
