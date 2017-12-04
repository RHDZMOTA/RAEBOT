package com.iteso.app.util;

import com.iteso.app.model.Word;

import java.util.HashMap;

@SuppressWarnings("all")
public class Dictionary {
    private static Dictionary instance = null;
    protected HashMap<String, Word> core = new HashMap<String, Word>();
    public HashMap<String, Word> getCore(){
        return this.core;
    }
    protected Dictionary(){}

    /**
     * Method to get or create an instance of Dictionary.
     * @return The unique dictionary instance.
     */
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    /**
     * Method to add a pair <String, Word> into the dictionary.
     * @param word A String value representing a word.
     * @param obj A Word object.
     */
    public static void add(String word, Word obj){
        if (!Dictionary.has(word)){
            Dictionary.getInstance().getCore().put(word.toLowerCase(), obj);
        }
    }

    /**
     * Method to add a Word instance into the dictionary.
     * @param obj A Word object.
     */
    public static void add(Word obj){
        if (!Dictionary.has(obj.getValue())){
            Dictionary.getInstance().getCore().put(obj.getValue().toLowerCase(), obj);
        }

    }

    /**
     * Method to add a word into the dictionary.
     * @param word A String value representing a word.
     */
    public static void add(String word){
        if (!Dictionary.has(word)){
            Word wordObj = new Word(word);
            Dictionary.getInstance().getCore().put(word.toLowerCase(), wordObj);
        }

    }

    /**
     * Method that indicates if the dictionary contains a word.
     * @param word A String value representing a word.
     * @return Boolean true if the dictionary has the word and false otherwise.
     */
    public static boolean has(String word){
        return Dictionary.getInstance().getCore().containsKey(word.toLowerCase()) && !Dictionary.hasError(word);
    }

    /**
     * Method that indicates if the dictionary contains a word with an error on its definition.
     * @param word A String value representing a word.
     * @return Boolean true if the dictionary contains an error word and false otherwise.
     */
    public static boolean hasError(String word) {
        if (Dictionary.getInstance().getCore().containsKey(word.toLowerCase())){
            boolean contains = Dictionary.getDefinition(word).matches(".*\\bUnexpected error\\b.*");
            return contains;
        }
        return false;
    }


    /**
     * Method to retreive a Word Object from the dictionary.
     * @param word A String value representing a word.
     * @return A Word Object corresponding to the word string.
     */
    public static Word get(String word){
        return Dictionary.getInstance().getCore().get(word);
    }

    /**
     * Method to retreive the definition of a word in the dictionary.
     * @param word A stirng value representing a word.
     * @return A String value representing a definition.
     */
    public static String getDefinition(String word){
        return Dictionary.get(word).getDefinition();
    }

    /**
     * Method to generate a String representation of the Dictionary.
     * @return A String value representing the dictionary.
     */
    @Override
    public String toString() {
        return Dictionary.getInstance().getCore().toString();
    }

    public static void main(String[] args){
        Word w1 = new Word("hola");
        Word w2 = new Word("instancia");
        Dictionary.add(w1);
        Dictionary.add(w2);
        Dictionary.add("hola");
        Dictionary.add("recurso");


        System.out.println(Dictionary.getInstance());
        System.out.println(Dictionary.has("recurso"));
    }
}
