package se.dibbler.backend.dto;

import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.generics.BaseDto;

@XmlRootElement(name = "authUser")
public class AuthUserDto extends BaseDto {

    private String googleId;
    private String facebookId;
    private String username;
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
