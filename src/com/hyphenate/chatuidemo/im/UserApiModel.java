package com.hyphenate.chatuidemo.im;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class UserApiModel implements Serializable {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DatabaseField(id = true)
    public String id;

    @DatabaseField
    public String Email;

    @DatabaseField
    public String HeadImg;

    @DatabaseField
    public String EaseMobUserName;

    @DatabaseField
    public String EaseMobPassword;




    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getEaseMobUserName() {
        return EaseMobUserName;
    }

    public void setEaseMobUserName(String easeMobUserName) {
        EaseMobUserName = easeMobUserName;
    }

    public String getEaseMobPassword() {
        return EaseMobPassword;
    }

    public void setEaseMobPassword(String easeMobPassword) {
        EaseMobPassword = easeMobPassword;
    }
}