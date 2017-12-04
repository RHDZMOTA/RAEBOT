package com.iteso.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.iteso.app.model.SmsTextMessage;
import com.iteso.app.model.TextResponse;
import com.iteso.app.util.AppConfigs;
import com.iteso.app.util.Jsonifier;
import com.iteso.app.util.Requests;
import com.twilio.twiml.Body;
import com.twilio.twiml.Media;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;


@SuppressWarnings("all")
public class SmsResponseController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String userMessage = request.getParameter("Body");
        String userPhone = request.getParameter("From");

        if (userMessage == null){
            response.setContentType("application/xml");
            String defaultResp = "<Response>\n<Message>\n<Body>Hello Stranger.</Body>\n</Message>\n</Response>";
            response.getWriter().print(defaultResp);
            return ;
        }

        if (userPhone == null)
            userPhone = AppConfigs.getInstance().twilioConf.getString("test_number");

        TextResponse textResponse = new TextResponse(userMessage, userPhone);
        String basicResponse = textResponse.getAnsewer();
        //if (textResponse.requiresDefinition()){
        //    textResponse.sendDef();
        //}

        System.out.println(basicResponse);
        Message sms;
        MessagingResponse twiml;
        try {
            sms = new Message.Builder()
                    .body(new Body(basicResponse))
                    //.media(new Media("https://demo.twilio.com/owl.png")) // not using media.
                    .build();
            twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
        } catch (Exception e) {
            sms = new Message.Builder()
                    .body(new Body("Oh Jeez! Unkown error: " + e.toString()))
                    .build();
            twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            new SmsTextMessage("+523318506323", "Error in SmsResponseController: " + e.toString());

        }

        response.setContentType("application/xml");
        try{
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            response.getWriter().print("Looks like we have a TwiMLException: " + e.toString());
        }

        //Requests.getJson("https://chatbot-rae.appspot.com/define?word=hola&send="
        //        + userPhone.replace("+", ""));
    }
}
