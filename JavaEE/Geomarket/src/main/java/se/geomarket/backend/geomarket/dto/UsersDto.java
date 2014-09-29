/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Defines a user for Dibbler")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDto extends BaseDto {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The username of the user", required = true)
    private String username;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The email of the user", required = true)
    private String email;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The users password", required = true)
    private String password;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The users gender", required = true)
    private String gender;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "The users age", required = false)
    private Integer age;

    @XmlElement(type = String.class, required = false)
    @ApiModelProperty(value = "URL to the users avatar or image", required = false)
    private String imageUrl;

    @XmlElement(type = Boolean.class, required = true)
    @ApiModelProperty(value = "A flag to see if the user is active or not", required = true)
    private Boolean active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
