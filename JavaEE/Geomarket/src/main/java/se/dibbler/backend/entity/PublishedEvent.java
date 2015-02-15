/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Spatial;
import org.hibernate.search.annotations.SpatialMode;
import org.hibernate.search.annotations.Store;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Spatial(spatialMode = SpatialMode.GRID)
@Entity
@Indexed
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.PUBLISHED_EVENT_FINDBY_EXPIRED_DATE, query = "SELECT c FROM PublishedEvent c where c.expires <:expiryDate"),})
public class PublishedEvent extends BaseEntity {

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @NotNull(message = "cannot be null")
    @Longitude
    private Double longitude;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @NotNull(message = "cannot be null")
    @Latitude
    private Double latitude;

    @NotNull(message = "cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    private Company company;

    @Transient
    private Language language;

    @Column
    @NotNull(message = "cannot be null")
    private String languageId;

    @Column
    @NotNull(message = "cannot be null")
    private String eventTypeId;

    @Column
    @NotNull(message = "cannot be null")
    private String categoryId;

    @Transient
    private Event event;

    @Column
    @NotNull(message = "cannot be null")
    private String eventId;

    @Column
    @NotNull(message = "cannot be null")
    
    private String companyName;
    @Column
    private String companyStreet;
    @Column
    private String companyStreetNr;
    @Column
    private String companyCity;
    @Column
    private String companyState;
    @Column
    private String companyCountry;
    @Column
    private Integer companyPostalCode;
    @Column
    private String companyWww;
    @Column
    private String companyPhone;
    @Column
    private Boolean active;

    @Column
    @NotNull(message = "cannot be null")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @DateBridge(resolution = Resolution.HOUR)
    private Date expires;

    @Column
    @NotNull(message = "cannot be null")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @DateBridge(resolution = Resolution.HOUR)
    private Date starts;

    @Column
    @NotNull(message = "cannot be null")
    private String heading;
    @Column
    @NotNull(message = "cannot be null")
    private String body;

    @Column
    private String imageUrl;

    @Column
    private String imageLargeUrl;

    @Column
    private String imageSmallUrl;

    @PreUpdate
    @Override
    protected void onUpdate() {
        this.companyName = company.getName();
        this.companyStreet = company.getStreet();
        this.companyStreetNr = company.getStreetNr();
        this.companyCity = company.getCity();
        this.companyState = company.getState();
        this.companyCountry = company.getCountry();
        this.companyPostalCode = company.getPostalCode();
        this.companyWww = company.getWww();
        this.companyPhone = company.getPhone();

        this.eventId = event.getExtId();
        this.categoryId = event.getCategory().getExtId();
        this.eventTypeId = event.getEventType().getExtId();
        this.languageId = language.getExtId();

        this.setUpdatedDate(new Date());
    }

    @PrePersist
    @Override
    protected void onCreate() {
        this.companyName = company.getName();
        this.companyStreet = company.getStreet();
        this.companyStreetNr = company.getStreetNr();
        this.companyCity = company.getCity();
        this.companyState = company.getState();
        this.companyCountry = company.getCountry();
        this.companyPostalCode = company.getPostalCode();
        this.companyWww = company.getWww();
        this.companyPhone = company.getPhone();

        this.eventId = event.getExtId();
        this.categoryId = event.getCategory().getExtId();
        this.eventTypeId = event.getEventType().getExtId();
        this.languageId = language.getExtId();

        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
        this.setExtId(UUID.randomUUID().toString());
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public String getCompanyStreetNr() {
        return companyStreetNr;
    }

    public void setCompanyStreetNr(String companyStreetNr) {
        this.companyStreetNr = companyStreetNr;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public Integer getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(Integer companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyWww() {
        return companyWww;
    }

    public void setCompanyWww(String companyWww) {
        this.companyWww = companyWww;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public Date getStarts() {
        return starts;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageLargeUrl() {
        return imageLargeUrl;
    }

    public void setImageLargeUrl(String imageLargeUrl) {
        this.imageLargeUrl = imageLargeUrl;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

}
