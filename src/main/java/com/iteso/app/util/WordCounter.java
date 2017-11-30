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
    public static WordCounter getInstance() {
        if (instance == null) {
            instance = new WordCounter();
        }
        return instance;
    }

    public static void add(String word){
        if (WordCounter.has(word)){
            int prevFreq = WordCounter.getInstance().getCore().get(word.toLowerCase());
            int updatedFreq = prevFreq+1;
            WordCounter.getInstance().getCore().put(word.toLowerCase(), updatedFreq);
        } else {
            WordCounter.getInstance().getCore().put(word.toLowerCase(), 1);
        }
    }

    public static boolean has(String word){
        return WordCounter.getInstance().getCore().containsKey(word.toLowerCase());
    }

    @Override
    public String toString() {
        return WordCounter.getInstance().getCore().toString();
    }

    public static void main(String[] args){
        WordCounter.add("hola");
        WordCounter.add("hola");
        WordCounter.add("hey");
        System.out.println(WordCounter.getInstance());
    }
}
