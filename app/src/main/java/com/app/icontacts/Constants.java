package com.app.icontacts;

/**
 * Created by saili on 4/3/2018.
 */

public class Constants {
    public static final String BASE_URL = "https://reqres.in";
    public static final String CREATE_USER_URL = "/api/users?page=";
    public static final String GET_USER_URL = "/api/users";

    public static final int MAX_WIDTH = 1024;
    public static final int MAX_HEIGHT = 768;
    public static final int IMG_SIZE = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));
}
