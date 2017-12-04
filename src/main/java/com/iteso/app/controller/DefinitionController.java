package com.iteso.app.controller;


import com.iteso.app.util.Dictionary;
import com.iteso.app.util.JsonSerializer;
import com.iteso.app.util.WordCounter;
import com.iteso.app.model.SmsTextMessage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("all")
public class DefinitionController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // Extract parameters form request
        String stringWord = req.getParameter("word");
        String stringSend = req.getParameter("send");


        // Add to the word counter and dictionary
        try{
            Dictionary.add(stringWord);
            WordCounter.add(stringWord);
        } catch (Exception e){
            System.out.println(e.toString());
        }


        // Get definition
        String definition = Dictionary.has(stringWord) ? Dictionary.getDefinition(stringWord) : "{\"Error\": \"Service unavailable (try later)\"}";

        // Send message if required
        if (stringSend != null){
            try {
                JsonSerializer jsonDef = new JsonSerializer(definition);
                String respDefinition = "\nWord detected: "
                        + jsonDef.getStringField("suggested-word")
                        + "\nPossible definitions (RAE): "
                        + jsonDef.getStringField("definitions");
                SmsTextMessage smsTextMessage = new SmsTextMessage("+" + stringSend, respDefinition);

                smsTextMessage.send();
            } catch (Exception e){
                new SmsTextMessage("+523318506323", e.toString()).send();
                System.out.println(e.getMessage());
            }
        }

        // Write out the response.
        resp.setContentType("application/json");
        resp.getWriter().println(definition);
    }
}
