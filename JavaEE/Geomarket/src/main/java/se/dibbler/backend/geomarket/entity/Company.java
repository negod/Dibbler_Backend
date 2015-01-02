/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import se.dibbler.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Company extends BaseEntity {

    @NotNull(message = "cannot be null and must be unique")
    @Column(unique = true)
    private String orgNr;
    @NotNull(message = "cannot be null")
    @Column
    private String name;
    @Column
    private String street;
    @Column
    private String streetNr;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String country;
    @Column
    private Integer postalCode;
    @Column
    private String followerClaim;
    @Column
    private String www;
    @Column
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    private Location location;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompanyUsers> companyUsers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    private List<Event> events;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Filter filter;

    @OneToOne
    private Company parentCompany;

    public String getOrgNr() {
        return orgNr;
    }

    public void setOrgNr(String orgNr) {
        this.orgNr = orgNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getFollowerClaim() {
        return followerClaim;
    }

    public void setFollowerClaim(String followerClaim) {
        this.followerClaim = followerClaim;
    }

    public List<CompanyUsers> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(List<CompanyUsers> companyUsers) {
        this.companyUsers = companyUsers;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Company getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(Company parentCompany) {
        this.parentCompany = parentCompany;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (location != null) {
            location.setCompany(this);
        }
    }

}
