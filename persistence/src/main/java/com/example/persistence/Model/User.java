package com.example.persistence.Model;

/**
 * Created by H_P on 2016/4/28.
 * @author luo
 * @version 1.0
 */
public class User {
    private String userName;
    private String profilePictureUrl;

    public String getUserName() {
        return userName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
