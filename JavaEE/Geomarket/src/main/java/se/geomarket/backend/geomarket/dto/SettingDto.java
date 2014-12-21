/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class SettingDto extends BaseDto {

    private UsersDto user;
    private Boolean mapAsDefault;
    private Boolean followOnTop;
    private Boolean allowPush;
    private LanguageDto language;

    public UsersDto getUser() {
        return user;
    }

    public void setUser(UsersDto user) {
        this.user = user;
    }

    public Boolean isMapAsDefault() {
        return mapAsDefault;
    }

    public void setMapAsDefault(Boolean mapAsDefault) {
        this.mapAsDefault = mapAsDefault;
    }

    public Boolean isFollowOnTop() {
        return followOnTop;
    }

    public void setFollowOnTop(Boolean followOnTop) {
        this.followOnTop = followOnTop;
    }

    public Boolean isAllowPush() {
        return allowPush;
    }

    public void setAllowPush(Boolean allowPush) {
        this.allowPush = allowPush;
    }

    public LanguageDto getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDto language) {
        this.language = language;
    }
    
    

}
