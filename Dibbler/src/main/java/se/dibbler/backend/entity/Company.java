/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Where;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.COMPANY_GET_BY_ORGNO, query = "SELECT c FROM Company c where c.orgNr =:orgNr")})
public class Company extends BaseEntity {

    @NotNull(message = "cannot be null and must be unique")
    @Column(unique = true) //this together with active should be unique
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
    @Column
    private String cellPhone;
    @Column
    private String imageUrl;
    @Column
    private String smallImageUrl;
    @Column
    private String largeImageUrl;
    @Column
    private String siteManager;
    @Column
    private Boolean billAsUnique;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, optional = false)
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @Where(clause = "active=1")
    private List<CompanyUsers> companyUsers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @Where(clause = "active=1")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @Where(clause = "active=1")
    private List<PublishedEvent> publishedEvents;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @Where(clause = "active=1")
    private List<LocationGroup> locationGroups;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @Where(clause = "active=1")
    private List<Location> locations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @Where(clause = "active=1")
    private List<Company> branchCompanies;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Filter filter;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @ManyToOne
    private Company parentCompany;

    @Override
    public void inactivate() {

        location.setActive(false);

        if (filter != null) {
            filter.setActive(false);
        }

        for (Event event : getEvents()) {
            event.setActive(false);
        }

        for (PublishedEvent pubEvent : getPublishedEvents()) {
            pubEvent.setActive(false);
        }

        for (LocationGroup locGroup : getLocationGroups()) {
            locGroup.setActive(false);
        }

        for (Location loc : getLocations()) {
            loc.setActive(false);
        }

    }

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
        if (companyUsers == null) {
            return new ArrayList<>();
        }
        return companyUsers;
    }

    public void setCompanyUsers(List<CompanyUsers> companyUsers) {
        this.companyUsers = companyUsers;
    }

    public List<Event> getEvents() {
        if (events == null) {
            return new ArrayList<>();
        }
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public List<PublishedEvent> getPublishedEvents() {
        if (publishedEvents == null) {
            return new ArrayList<>();
        }
        return publishedEvents;
    }

    public void setPublishedEvents(List<PublishedEvent> publishedEvents) {
        this.publishedEvents = publishedEvents;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public List<LocationGroup> getLocationGroups() {
        if (locationGroups == null) {
            return new ArrayList<>();
        }
        return locationGroups;
    }

    public void setLocationGroups(List<LocationGroup> locationGroups) {
        this.locationGroups = locationGroups;
    }

    public List<Location> getLocations() {
        if (locations == null) {
            return new ArrayList<>();
        }
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getSiteManager() {
        return siteManager;
    }

    public void setSiteManager(String siteManager) {
        this.siteManager = siteManager;
    }

    public List<Company> getBranchCompanies() {
        if (branchCompanies == null) {
            return new ArrayList<>();
        }
        return branchCompanies;
    }

    public void setBranchCompanies(List<Company> branchCompanies) {
        this.branchCompanies = branchCompanies;
    }

    public Boolean getBillAsUnique() {
        return billAsUnique;
    }

    public void setBillAsUnique(Boolean billAsUnique) {
        this.billAsUnique = billAsUnique;
    }

}
