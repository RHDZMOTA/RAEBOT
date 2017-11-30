package com.iteso.app.util;


import java.io.IOException;


@SuppressWarnings("all")
public class Word {

    private final String APIURL = "https://rae-wrapper-api.herokuapp.com/rae/desc/";
    private String value;
    private String definition;

    public Word(String val){
        setValue(val);
        setDefinition();
    }

    public String getStringUrl(String word){
        return APIURL + word;
    }

    public String getValue(){ return value;}
    public void setValue(String word){
        this.value = word;
    }
    public String getDefinition(){ return definition;}
    public void setDefinition(){
        JsonSerializer jsonSerializer = getFromRAE(value); ;
        String word = getValue();
        this.definition = jsonSerializer.toString();
    }

    private JsonSerializer getFromRAE(String val) {
        // https://stackoverflow.com/questions/7352748/how-to-set-connection-timeouts-in-google-app-engine
        // https://cloud.google.com/appengine/docs/standard/java/config/appref#urlfetch_timeout
        // https://cloud.google.com/appengine/docs/standard/java/issue-requests#Fetching_URLs_with_java_net
        // http://localhost:8080/demo
        // https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/appengine/urlfetch/src/main/java/com/example/appengine/UrlFetchServlet.java
        String wordUrl = getStringUrl(val);
        JsonSerializer jsonResp = Requests.getJson(wordUrl);
        return jsonResp;
    }

    static public void main(String[] args) throws IOException {
        Word word = new Word("hola");
        System.out.println(word.getDefinition());
    }
}
