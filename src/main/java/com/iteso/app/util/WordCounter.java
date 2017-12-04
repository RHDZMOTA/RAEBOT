package com.iteso.app.util;

import java.util.HashMap;

@SuppressWarnings("all")
public class WordCounter {
    private static WordCounter instance = null;
    protected HashMap<String, Integer> core = new HashMap<String, Integer>();
    public HashMap<String, Integer> getCore(){
        return this.core;
    }
    public WordCounter(){};

    /**
     * Method that creates or retreives an instance of the counter.
     * @return Unique instance of WordCounter Object.
     */
    public static WordCounter getInstance() {
        if (instance == null) {
            instance = new WordCounter();
        }
        return instance;
    }

    /**
     * Method to add/registed a word in the counter given a string value.
     * @param word String value that represents a word.
     */
    public static void add(String word){
        if (WordCounter.has(word)){
            int prevFreq = WordCounter.getInstance().getCore().get(word.toLowerCase());
            int updatedFreq = prevFreq+1;
            WordCounter.getInstance().getCore().put(word.toLowerCase(), updatedFreq);
        } else {
            WordCounter.getInstance().getCore().put(word.toLowerCase(), 1);
        }
    }

    /**
     * Method that indicates if the counter has a register of a given string value.
     * @param word String value representing a word.
     * @return True is word is in WordCounter and false otherwise.
     */
    public static boolean has(String word){
        return WordCounter.getInstance().getCore().containsKey(word.toLowerCase());
    }

    /**
     * Method that creates a string representation of WordCounter.
     * @return String value representing the WordCounter.
     */
    @Override
    public String toString() {
        return WordCounter.getInstance().getCore().toString();
    }
}
