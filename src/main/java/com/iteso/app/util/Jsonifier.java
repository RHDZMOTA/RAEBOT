package com.iteso.app.util;

import com.google.gson.Gson;

@SuppressWarnings("all")
public class Jsonifier {
    /**
     * Method that creates a serialized Json from an Object.
     * @param object A serializable Object.
     * @return String value representing the Json version of the object.
     */
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
