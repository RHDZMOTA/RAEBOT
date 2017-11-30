package com.iteso.app.util;

import java.util.HashMap;

@SuppressWarnings("all")
public class Dictionary {
    private static Dictionary instance = null;
    protected HashMap<String, Word> core = new HashMap<String, Word>();
    public HashMap<String, Word> getCore(){
        return this.core;
    }
    protected Dictionary(){}
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    public static void add(String word, Word obj){
        if (!Dictionary.has(word)){
            Dictionary.getInstance().getCore().put(word.toLowerCase(), obj);
        }
    }

    public static void add(Word obj){
        if (!Dictionary.has(obj.getValue())){
            Dictionary.getInstance().getCore().put(obj.getValue().toLowerCase(), obj);
        }

    }

    public static void add(String word){
        if (!Dictionary.has(word)){
            Word wordObj = new Word(word);
            Dictionary.getInstance().getCore().put(word.toLowerCase(), wordObj);
        }

    }

    public static boolean has(String word){
        return Dictionary.getInstance().getCore().containsKey(word.toLowerCase()) && !Dictionary.hasError(word);
    }

    public static boolean hasError(String word) {
        if (Dictionary.getInstance().getCore().containsKey(word.toLowerCase())){
            boolean contains = Dictionary.getDefinition(word).matches(".*\\bUnexpected error\\b.*");
            return contains;
        }
        return false;
    }


    public static Word get(String word){
        return Dictionary.getInstance().getCore().get(word);
    }

    public static String getDefinition(String word){
        return Dictionary.get(word).getDefinition();
    }

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
