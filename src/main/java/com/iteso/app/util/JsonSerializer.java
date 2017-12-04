package com.iteso.app.util;

import org.json.JSONObject;

@SuppressWarnings("all")
public class JsonSerializer {
    private String contents;
    private JSONObject jsonObject;

    /**
     * Constructor of the JsonSerializer in order to initialize the instance with the string content and Json Transform.
     * @param contents String value representing the contents of the Json.
     */
    public JsonSerializer(String contents){
        setContents(contents);
        setJsonObject();
    }

    public String getContents() { return contents; }

    public void setContents(String contents) { this.contents = contents; }

    public JSONObject getJsonObject() { return jsonObject; }

    /**
     * Creates the JsonObject given the String value of contents.
     */
    public void setJsonObject() {
        this.jsonObject = new JSONObject(getContents());
    }

    /**
     * Method to perform queries and retreive String values from a given JsonObject label/field.
     * @param label String value representing the field or label in the Json.
     * @return String value representing the String content in the label.
     */
    public String getStringField(String label){
        return getJsonObject().getString(label);
    }

    /**
     * Method to perform queries and retreive JsonObjects from a given JsonObject label/field.
     * @param label String value representing the field or label in the Json.
     * @return JsonObject instance representing the Json contained in the label.
     */
    public JSONObject getJsonField(String label){
        return getJsonObject().getJSONObject(label);
    }

    /**
     * Method that creates a string representation of the JsonObject.
     * @return String value representing the Json structure.
     */
    @Override
    public String toString(){
        return getContents();
    }
}
