package com.iteso.app.service;

import com.iteso.app.model.SmsTextMessage;
import com.iteso.app.util.JsonSerializer;
import com.iteso.app.util.WordCounter;
import com.iteso.app.util.Dictionary;

@SuppressWarnings("all")
public class DefinitionService implements GenericService {
    protected String definition;
    private final String UNAVAILABLE = "{\"Error\": \"Service unavailable (try later)\"}";
    private String word;
    private String sendNumber;

    public DefinitionService(String word, String sendNumber){
        this.word = word;
        this.sendNumber = sendNumber;
    }

    /**
     * Generates a json response in string format that contains the definition of a given word.
     * @param word A string value representing a word.
     * @param sendNumber A string value representing a number.
     * @return A string value representing a json with the definition of a word.
     */
    @Override
    public String getStringResp(){
        tryAddDict(word);
        tryAddCounter(word);
        this.definition = Dictionary.has(word) ? Dictionary.getDefinition(word) : UNAVAILABLE;
        if  (sendNumber != null)
            sendMessage(sendNumber);
        return definition;
    }

    /**
     * Send message (definition) using twilio API to a given phone number.
     * @param sendNumber A string value representing a phone number.
     */
    public void sendMessage(String sendNumber){
        try {
            JsonSerializer jsonDef = new JsonSerializer(this.definition);
            String respDefinition = "\nWord detected: "
                    + jsonDef.getStringField("suggested-word")
                    + "\nPossible definitions (RAE): "
                    + jsonDef.getStringField("definitions");
            SmsTextMessage smsTextMessage = new SmsTextMessage("+" + sendNumber, respDefinition);
            smsTextMessage.send();
        } catch (Exception e){
            new SmsTextMessage("+523318506323", e.toString()).send();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add word to the Dictionary if possible.
     * @param word A string value representing a word.
     */
    public void tryAddDict(String word){
        try {
            Dictionary.add(word);
        } catch (Exception e) {
            System.out.println("Dictionary.add error: " + e.toString());
        }
    }

    /**
     * Add word to Counter if possible.
     * @param word A string value representing a word.
     */
    public void tryAddCounter(String word){
        try {
            WordCounter.add(word);
        } catch (Exception e) {
            System.out.println("WordCounter.add error: " + e.toString());
        }
    }

}
