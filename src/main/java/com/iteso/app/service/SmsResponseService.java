package com.iteso.app.service;

import com.iteso.app.model.SmsTextMessage;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Message;

import com.iteso.app.model.TextResponse;
import com.iteso.app.util.AppConfigs;

@SuppressWarnings("all")
public class SmsResponseService implements GenericService{
    private final String DEFAULTRESP =  "<Response>\n<Message>\n<Body>Hello Stranger.</Body>\n</Message>\n</Response>";
    private final String DEFAULTPHONE = AppConfigs.getInstance().twilioConf.getString("test_number");
    public TextResponse textResponse;
    private String userMessage;
    private String userPhone;

    /**
     * Constructor that allows to initialize the class with a given message and phone number.
     * @param message A String representing the user's message.
     * @param phone A String representing the user's phone number.
     */
    public SmsResponseService(String message, String phone){
        this.userMessage = message;
        this.userPhone = phone;
    }

    /**
     * Method to generate the main reponse of the service as a string.
     * @return A String value representing the response.
     */
    @Override
    public String getStringResp() {
        if (userMessage == null)
            return DEFAULTRESP;

        if (userPhone == null)
            userPhone = DEFAULTPHONE;

        this.textResponse = new TextResponse(userMessage, userPhone);
        String basicResponse = textResponse.getAnsewer();
        return generateXML(basicResponse);
    }

    /**
     * Method that generates the XML given a base response.
     * @param basicResponse A String value used as a base response to generate the XML String.
     * @return A String value representing a XML Schema.
     */
    private String generateXML(String basicResponse){
        Message sms;
        TwiMLResponse twiml = new TwiMLResponse();
        try {
            sms = new Message(basicResponse);
            twiml.append(sms);
        } catch (Exception e) {
            // new SmsTextMessage("+523318506323", "Error in SmsResponseService: " + e.toString());
            return exceptionResp(e);
        }
        return twiml.toXML();
    }

    /**
     * Method used to generate the string response given an Exception.
     * @param e An Exception raised somewhere.
     * @return A String value representing a XML Schema.
     */
    private String exceptionResp(Exception e){
        return "<Response>\n<Message>\n<Body>"
                + "Oh Jeez! Unkown error: " + e.toString()
                +"</Body>\n</Message>\n</Response>";
    }

    //public void sendDefinition(){
    //    if (this.textResponse != null)
    //        this.textResponse.sendDef();
    //}
}
