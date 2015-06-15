/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import se.dibbler.backend.constants.EventRecipientType;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Event extends BaseEntity {
    
    @OneToOne()
    @NotNull(message = "cannot be null must be an existing language")
    private Language defaultLanguage;
    
    @NotNull(message = "cannot be null, must be an existing company")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    
    @NotNull(message = "cannot be null, must be an existing category")
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;
    
    @NotNull(message = "cannot be null, must be an existing eventType")
    @OneToOne(fetch = FetchType.LAZY)
    private EventType eventType;
    
    @NotNull(message = "cannot be null")
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startDate;
    
    @NotNull(message = "cannot be null")
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column
    private String qrCode;
    
    @Column
    private Integer maxRedeem;
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    List<EventText> eventTexts;
    
    @Column
    private String imageUrl;
    
    @Column
    private String imageLargeUrl;
    
    @Column
    private String imageSmallUrl;

    //@Convert(converter = EventRecipientConverter.class)
    private EventRecipientType recipientType;
    
    public Language getDefaultLanguage() {
        return defaultLanguage;
    }
    
    public void setDefaultLanguage(Language defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public EventType getEventType() {
        return eventType;
    }
    
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    
    public Integer getMaxRedeem() {
        return maxRedeem;
    }
    
    public void setMaxRedeem(Integer maxRedeem) {
        this.maxRedeem = maxRedeem;
    }
    
    public List<EventText> getEventTexts() {
        return eventTexts;
    }
    
    public void setEventTexts(List<EventText> eventTexts) {
        this.eventTexts = eventTexts;
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
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public EventRecipientType getRecipientType() {
        return recipientType;
    }
    
    public void setRecipientType(EventRecipientType recipientType) {
        this.recipientType = recipientType;
    }
    
    @Override
    public void inactivate() {
        this.setActive(false);
    }
    
}
