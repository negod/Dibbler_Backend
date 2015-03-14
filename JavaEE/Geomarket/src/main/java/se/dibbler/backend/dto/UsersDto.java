/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import se.dibbler.backend.dto.summary.UserSummaryDto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Extra user data")
@XmlRootElement(name = "userExtended")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDto extends UserSummaryDto {

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The users password", required = true)
    private String password;
    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "Optional user google id if logged in via google", required = true)
    private String googleId;
    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "Optional user facebook id if logged in via facebook", required = true)
    private String facebookId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
