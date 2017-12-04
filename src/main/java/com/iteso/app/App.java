package com.iteso.app;


import com.iteso.app.model.AnswerTypes;
import com.iteso.app.model.TextResponse;
import com.iteso.app.model.Word;
import com.iteso.app.util.Dictionary;
import com.iteso.app.util.WordCounter;

@SuppressWarnings("all")
public class App {

    /**
     * Perform tests on relevant elements.
     */
    public static void main(String[] args){

        // TEST WORD
        System.out.println("\n--------- TEST : Word");
        Word word = new Word("hola");
        System.out.println(word.getDefinition());

        // TEST WORDCOUNTER
        System.out.println("\n--------- TEST : WordCounter");
        WordCounter.add("hola");
        WordCounter.add("hola");
        WordCounter.add("hey");
        System.out.println(WordCounter.getInstance());

        // TEST DICITONARY
        System.out.println("\n--------- TEST : Dictionary");
        Word w1 = new Word("hola");
        Word w2 = new Word("instancia");
        Dictionary.add(w1);
        Dictionary.add(w2);
        Dictionary.add("hola");
        Dictionary.add("recurso");
        System.out.println(Dictionary.getInstance());
        System.out.println(Dictionary.has("recurso"));

        // TEST ANSWER TPYES
        System.out.println("\n--------- TEST : AnswerTypes");
        System.out.println(AnswerTypes.GREETING);
        System.out.println(AnswerTypes.GREETING);
        System.out.println(AnswerTypes.GREETING);


        // TEST TEXTRESPONSE
        System.out.println("\n--------- TEST : WordCounter");
        TextResponse textResponse = new TextResponse("Esto es programación en JAVA.", "523318506323");
        System.out.println(textResponse.getAnsewer());
    }
}
