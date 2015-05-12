/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Filter;

import se.dibbler.generic.entity.BaseEntity;
import se.dibbler.generic.queries.DibblerNamedQueries;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.USERS_FINDBY_FACEBOOK_ID, query = "SELECT u FROM User u where u.facebookId = :facebookId"),
    @NamedQuery(name = DibblerNamedQueries.USERS_FINDBY_GOOGLE_ID, query = "SELECT u FROM User u where u.googleId = :googleId"),
    @NamedQuery(name = DibblerNamedQueries.USERS_AUTHENTICATE, query = "SELECT u FROM User u where u.email = :username and u.password = :password")
})
public class User extends BaseEntity {

    @Column
    private String displayName;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String salt;
    @Column
    private String gender;
    @Column
    private Date birthday;
    @Column
    private String imageUrl;
    @Column(unique = true)
    private String googleId;
    @Column(unique = true)
    private String facebookId;
    @OneToOne(fetch = FetchType.LAZY)
    private Filter filter;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public void inactivate() {
    }

}
