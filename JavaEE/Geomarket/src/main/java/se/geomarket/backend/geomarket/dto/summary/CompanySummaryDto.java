/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto.summary;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "A definition of a company")
@XmlRootElement(name = "companySummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySummaryDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The name of the company", required = true)
    private String name;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The street where the company is registered", required = false)
    private String street;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The streetNumber number where the company is registered", required = false)
    private String streetNr;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The city where the company is registered", required = false)
    private String city;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The state/province where the company is registered", required = false)
    private String state;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The country where the company is registered", required = true)
    private String country;

    @XmlElement(type = Integer.class, required = false)
    @ApiModelProperty(value = "The postal code where the company is registered", required = false)
    private Integer postalCode;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The web address of the company", required = false)
    private String www;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The phone number of the company", required = false)
    private String phone;

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
    
}