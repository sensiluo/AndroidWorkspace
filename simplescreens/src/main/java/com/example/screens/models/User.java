package com.example.screens.models;

/**
 * Created by H_P on 2016/4/4.
 * @version 1.0
 * @author luo
 */
public class User {
    private String mUserName;
    private int mAge;
    private int mPhoneNumber;

    public User(String userName,int age,int phoneNumber){
        this.mUserName = userName;
        this.mAge = age;
        this.mPhoneNumber = phoneNumber;
    }

    public String getmUserName() {
        return mUserName;
    }

    public int getmAge() {
        return mAge;
    }

    public int getmPhoneNumber() {
        return mPhoneNumber;
    }
}
