package com.iteso.app.model;

import com.iteso.app.util.AppConfigs;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@SuppressWarnings("all")
public class SmsTextMessage {
    private final String ACCOUNT_SID = AppConfigs.getInstance().twilioConf.getString("ACCOUNT_SID");
    private final String AUTH_TOKEN = AppConfigs.getInstance().twilioConf.getString("AUTH_TOKEN");
    public static String TNUMBER = AppConfigs.getInstance().twilioConf.getString("TNUMBER");

    protected String phoneNumber;
    protected String stringContent;

    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setStringContent(String content){this.stringContent=content;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getStringContent(){return stringContent;}

    /**
     * Constructor to initialize SmsTextMessage with a phone and string content.
     * @param phone String contatining the phone number of the receiver.
     * @param content String containing the body of the message (content).
     */
    public SmsTextMessage(String phone, String content){
        setPhoneNumber(phone);
        setStringContent(content);
    }

    /**
     * Send method that uses the Twilio API to create and send a text message via SMS.
     * @return Returns a string representing the message id.
     */
    public String send(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(getPhoneNumber()),
                new PhoneNumber(TNUMBER),
                getStringContent())
                .create();
        return message.getSid();
    }

    public static void main(String[] args){
        SmsTextMessage smsTextMessage = new SmsTextMessage("+523318506323", "Hey there, esto es una prueba.");
        System.out.println(smsTextMessage.send());
    }
}
