package com.app.icontacts.data;

/**
 * Created by saili on 4/3/2018.
 */

public class Data {
    private String id;

    private String first_name;

    private String last_name;

    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", first_name = " + first_name + ", last_name = " + last_name + ", avatar = " + avatar + "]";
    }
}
