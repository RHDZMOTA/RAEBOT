package com.iteso.app.util;

import com.google.gson.Gson;

@SuppressWarnings("all")
public class Jsonifier {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
