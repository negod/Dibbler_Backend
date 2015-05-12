/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import se.dibbler.generic.user.User;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@MappedSuperclass
@FilterDef(name = "getOnlyActive",
        parameters = {
            @ParamDef(name = "isActive", type = "active")})
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = true)
    private Long id;
    @NotNull(message = "Cannot be null should be set to UUID")
    @Column(unique = true, updatable = false, insertable = true)
    @Pattern(regexp = "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
    private String extId;
    @Column
    @NotNull(message = "cannot be null")
    private boolean active;
    @NotNull(message = "cannot be null")
    @Column(updatable = false, insertable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    @NotNull(message = "cannot be null")
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToOne
    private User createdUser;
    @OneToOne
    private User updatedUser;

    public abstract void inactivate();

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.extId = UUID.randomUUID().toString();
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
