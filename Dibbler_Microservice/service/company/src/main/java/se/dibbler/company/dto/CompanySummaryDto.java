/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.company.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.generic.dto.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "companySummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySummaryDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    private String name;

    @XmlElement(type = String.class, required = false)
    private String street;

    @XmlElement(type = String.class, required = false)
    private String streetNr;

    @XmlElement(type = String.class, required = false)
    private String city;

    @XmlElement(type = String.class, required = false)
    private String state;

    @XmlElement(type = String.class, required = true)
    private String country;

    @XmlElement(type = Integer.class, required = false)
    private Integer postalCode;

    @XmlElement(type = String.class, required = false)
    private String www;

    @XmlElement(type = String.class, required = false)
    private String phone;

    @XmlElement(type = String.class, required = false)
    private String cellPhone;

    @XmlElement(type = String.class, required = false)
    private String imageUrl;

    @XmlElement(type = String.class, required = false)
    private String smallImageUrl;

    @XmlElement(type = String.class, required = false)
    private String largeImageUrl;

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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
}
