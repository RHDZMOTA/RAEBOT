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

    /**
     * Method used to set the input usually given by a user string message.
     * @param input A String value representing the input.
     */
    public void setInput(String input){
        this.input = input
                .replace(".", "")
                .replace("!", "")
                .replace("¡", "")
                .replace("?", "")
                .replace("¿", "");
    }

    public void setOutput(){this.output = getAnsewer();}
    public String getInput(){ return input;}
    public String getOutput(){ return output;}

    /**
     * Constructor to initialize the instance with an initial input and the corresponding userPhone.
     * @param input A String value representing the input.
     * @param userPhone A String value representign the user's phone.
     */
    public TextResponse(String input, String userPhone){
        setInput(input);
        this.userPhone = userPhone;
        setOutput();
    }

    /**
     * Method used to find the match among a word in a string of words separated by a space.
     * @param match A String value representing a word.
     * @param text A String value representing a set of words concatenated by a space.
     * @return Boolean true if the text contains the word and false otherwise.
     */
    public boolean findMatch(String match, String text){
        String[] words = text.split(" ");
        for (String word: words){
            if (word.toLowerCase().equals(match.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * Method that indicates if the input requires to get a definition from the Dictionary.
     * @return Boolean true if the input requires a definition and false otherwise.
     */
    public boolean requiresDefinition(){
        return findMatch("define", this.input.toLowerCase());
    }

    /**
     * Method used to generate the answer (output) for a given string input.
     * @return A String value representing the answer.
     */
    public String getAnsewer(){
        if (requiresDefinition()) {
            String[] splitStr = this.input.split(" ");
            if (splitStr.length < 2)
                splitStr = splitStr[0].split("%20");
            if (splitStr.length < 2)
                return "Coudn't understand word.";
            defineWord = splitStr[1].replace(" ", "");
            String respDefinition;
            try {
                //respDefinition = "Hold tight!";
                Dictionary.add(defineWord);
                JsonSerializer jsonDef = new JsonSerializer(Dictionary.getDefinition(defineWord));
                respDefinition = "\nWord detected: "
                        + jsonDef.getStringField("suggested-word")
                        + "\nPossible definitions (RAE): "
                        + jsonDef.getStringField("definitions");
                try {
                    WordCounter.add(defineWord);
                } catch (Exception e) {
                    System.out.println("Counter failed.");
                }

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


    //public void sendDef(){
    //    String localServerUrl = "http://localhost:8080/define?word=";
    //    String productionServerUrl = "https://chatbot-rae.appspot.com/define?word=";
    //    try {
    //        if (defineWord != null ){
    //            System.out.println("Something");
    //            Requests.getJson( productionServerUrl
    //                    + defineWord + "&send="
    //                    + userPhone.replace("+", ""));
    //        }
    //
    //    } catch (Exception e) {
    //        System.out.println(e);
    //    }
    //}

}
