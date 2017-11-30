package com.iteso.app.util;

import org.json.JSONObject;

@SuppressWarnings("all")
public class JsonSerializer {
    private String contents;
    private JSONObject jsonObject;

    public JsonSerializer(String contents){
        setContents(contents);
        setJsonObject();
    }

    public String getContents() { return contents; }

    public void setContents(String contents) { this.contents = contents; }

    public JSONObject getJsonObject() { return jsonObject; }

    public void setJsonObject() {
        this.jsonObject = new JSONObject(getContents());
    }

    public String getStringField(String label){
        return getJsonObject().getString(label);
    }

    public JSONObject getJsonField(String label){
        return getJsonObject().getJSONObject(label);
    }

    @Override
    public String toString(){
        return getContents();
    }
}
