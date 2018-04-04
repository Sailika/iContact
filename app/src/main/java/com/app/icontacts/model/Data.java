package com.app.icontacts.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saili on 4/3/2018.
 */

public class Data {
    private String id;


    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", firstName = " + firstName + ", lastName = " + lastName + ", avatar = " + avatar + "]";
    }
}
