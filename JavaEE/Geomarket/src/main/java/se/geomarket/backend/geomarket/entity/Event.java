/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Event extends BaseEntity {

    @NotNull(message = "event.company cannot be null, must be an existing company")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @NotNull(message = "event.category cannot be null, must be an existing category")
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;
    @NotNull(message = "event.eventType cannot be null, must be an existing eventType")
    @OneToOne(fetch = FetchType.LAZY)
    private EventType eventType;
    @NotNull(message = "event.eventText cannot be null")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EventText eventText;
    @NotNull(message = "event.startDate cannot be null")
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @NotNull(message = "event.endDate cannot be null")
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Column
    private String qrCode;
    @Column
    private Integer maxRedeem;

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

    public EventText getEventText() {
        return eventText;
    }

    public void setEventText(EventText eventText) {
        this.eventText = eventText;
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

}
