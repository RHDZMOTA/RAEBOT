package com.iteso.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.iteso.app.util.JsonSerializer;

@SuppressWarnings("all")
public class Requests {

    public static int timeoutMs = 45000;


    private static class Request {
        private String url;
        private JsonSerializer response;

        public Request(String url){
            setUrl(url);
        }

        public void setUrl(String url){
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setResponse(JsonSerializer resp){
            this.response = resp;
        }

        public JsonSerializer getResponse(){
            return response;
        }

        public void performGet(int timeoutMs) throws IOException {
            URL url = new URL(getUrl());
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(timeoutMs);
            conn.setReadTimeout(timeoutMs);
            InputStream stream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer json = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();
            setResponse(new JsonSerializer(json.toString()));
        }
    }

    public static JsonSerializer getJson(String url){
        Request r = new Request(url);

        JsonSerializer jsonResp;
        try {
            r.performGet(Requests.timeoutMs);
            jsonResp = r.getResponse();
        } catch (IOException e) {
            jsonResp = new JsonSerializer("{\"error\": " + "\"" + e.getMessage() + "\"");
        }
        return jsonResp;
    }
}
