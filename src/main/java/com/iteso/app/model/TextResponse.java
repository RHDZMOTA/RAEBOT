package com.iteso.app.model;


import com.iteso.app.util.Dictionary;
import com.iteso.app.util.JsonSerializer;
import com.iteso.app.util.Requests;
import com.iteso.app.util.WordCounter;

@SuppressWarnings("all")
public class TextResponse {
    private String input;
    private String output;
    private String userPhone;
    private String defineWord = null;
    public void setInput(String input){
        this.input = input
                .replace(".", "")
                .replace("!", "")
                .replace("¡", "")
                .replace("?", "")
                .replace("¿", "");}
    public void setOutput(){this.output = getAnsewer();}
    public String getInput(){ return input;}
    public String getOutput(){ return output;}

    public TextResponse(String input, String userPhone){
        setInput(input);
        this.userPhone = userPhone;
        setOutput();

    }

    public boolean findMatch(String match, String text){
        String[] words = text.split(" ");
        for (String word: words){
            if (word.toLowerCase().equals(match.toLowerCase()))
                return true;
        }
        return false;
    }

    public boolean requiresDefinition(){
        return findMatch("define", this.input);
    }

    public String getAnsewer(){
        if (requiresDefinition()) {
            defineWord = this.input.split("define")[1].replace(" ", "");
            String respDefinition;
            try {
                //respDefinition = "Hold tight!";

                Dictionary.add(defineWord);
                JsonSerializer jsonDef = new JsonSerializer(Dictionary.getDefinition(defineWord));
                respDefinition = "\nWord detected: "
                        + jsonDef.getStringField("suggested-word")
                        + "\nPossible definitions (RAE): "
                        + jsonDef.getStringField("definitions");
                WordCounter.add(defineWord);
            } catch (Exception e) {
                respDefinition = "\nInvalid word (or RAE API Error). "
                        + "I can only get the definition for symple words. Try again later or request other word. "
                        + "Java Catch:" + e.toString();
            }
            return respDefinition;
        }
        for (String w: new String[]{"hello", "hola", "tal", "onda"}){
            if (findMatch(w, this.input))
                return AnswerTypes.GREETING.toString();
        }
        for (String w: new String[]{"adios", "goodbye", "bye", "hasta"}){
            if (findMatch(w, this.input))
                return AnswerTypes.GOODBYE.toString();
        }
        for (String w: new String[]{"yes", "okay", "ok", "yeah", "si", "sii"}){
            if (findMatch(w, this.input))
                return AnswerTypes.AFIRMATION.toString();
        }
        for (String w: new String[]{"no", "never", "hell", "non"}){
            if (findMatch(w, this.input))
                return AnswerTypes.NEGATION.toString();
        }
        return AnswerTypes.GENERIC.toString();
    }


    public void sendDef(){
        String localServerUrl = "http://localhost:8080/define?word=";
        String productionServerUrl = "https://chatbot-rae.appspot.com/define?word=";
        try {
            if (defineWord != null ){
                System.out.println("Something");
                Requests.getJson( productionServerUrl
                        + defineWord + "&send="
                        + userPhone.replace("+", ""));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        TextResponse textResponse = new TextResponse("define hola", "523318506323");
        System.out.println(textResponse.getAnsewer());
        //textResponse.sendDef();
    }
}
