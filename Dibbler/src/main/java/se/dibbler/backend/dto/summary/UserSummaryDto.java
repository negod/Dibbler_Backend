/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.summary;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import se.dibbler.backend.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Defines a user for Dibbler")
@XmlRootElement(name = "userSummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSummaryDto extends BaseDto {


    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The display name of the user", required = true)
    private String displayName;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The email of the user", required = true)
    private String email;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The users gender", required = true)
    private String gender;

    @XmlElement(type = Date.class, required = false)
    @ApiModelProperty(value = "The users birthday", required = false)
    private Date birthday;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "URL to the users avatar or image", required = false)
    private String imageUrl;

    @XmlElement(required = true)
    @ApiModelProperty(value = "A flag to see if the user is active or not", required = true)
    private boolean active;

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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
