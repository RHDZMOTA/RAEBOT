package com.iteso.app.model;


import com.iteso.app.util.JsonSerializer;
import com.iteso.app.util.Requests;

import java.io.IOException;


@SuppressWarnings("all")
public class Word {

    private final String APIURL = "https://rae-wrapper-api.herokuapp.com/rae/desc/";
    private String value;
    private String definition;

    /**
     * Constructor to inialize a Word instance with a value (string word) and set the definition.
     * @param val A String value representing a word.
     */
    public Word(String val){
        setValue(val);
        setDefinition();
    }

    /**
     * Method to retreive the RAE-API-URL for a given word.
     * @param word A String value representing a word.
     * @return
     */
    public String getStringUrl(String word){
        return APIURL + word;
    }


    public String getValue(){ return value;}
    public void setValue(String word){
        this.value = word;
    }
    public String getDefinition(){ return definition;}

    /**
     * Method used to retreive the definition of a given word (request to API)
     */
    public void setDefinition(){
        JsonSerializer jsonSerializer = getFromRAE(value); ;
        String word = getValue();
        this.definition = jsonSerializer.toString();
    }

    /**
     * Method used to get the response from the RAE API for a given word.
     * @param val String value representing a word.
     * @return
     */
    private JsonSerializer getFromRAE(String val) {
        String wordUrl = getStringUrl(val);
        JsonSerializer jsonResp = Requests.getJson(wordUrl);
        return jsonResp;
    }

}
