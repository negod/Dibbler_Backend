/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.generics;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @NotNull(message = "Cannot be null!!, should be autoincrement!!")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = true)
    private long id;
    @NotNull(message = "Cannot be null should be set to UUID")
    @Column(unique = true, updatable = false, insertable = true)
    @Pattern(regexp = "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
    private String extId;
    @NotNull(message = "cannot be null")
    @Column(updatable = false, insertable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    @NotNull(message = "cannot be null")
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updatedDate;

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.extId = UUID.randomUUID().toString();
    }

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
