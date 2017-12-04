package com.iteso.app.service;

import com.iteso.app.util.Jsonifier;
import com.iteso.app.util.WordCounter;

@SuppressWarnings("all")
public class FrecuencyService implements GenericService {

    /**
     * Generates a json response in string format that contains information about the WordCounter object.
     * @return String value representing a json structure.
     */
    @Override
    public String getStringResp(){
        return Jsonifier.toJson(WordCounter.getInstance().getCore());
    }
}
