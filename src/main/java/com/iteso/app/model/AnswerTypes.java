package com.iteso.app.model;

import java.util.Random;

@SuppressWarnings("all")
public enum AnswerTypes {
    GREETING(new String[]{
            "\n(RAEBOT) Hello there :)",
            "\n(RAEBOT) Greeting Sir!",
            "\n(RAEBOT) Whats up bro!"}),
    GOODBYE(new String[]{
            "\n(RAEBOT) Okay, see you soon!",
            "\n(RAEBOT) Great! Hasta la vista.",
            "\n(RAEBOT) Have a nice day, kind human."}),
    NEGATION(new String[]{
            "\n(RAEBOT) Totally.",
            "\n(RAEBOT) I'm 100% sure of that.",
            "\n(RAEBOT) For sure.",
            "\n(RAEBOT) Agreed."}),
    AFIRMATION(new String[]{
            "\n(RAEBOT) Def! ;)",
            "\n(RAEBOT) No way.",
            "\n(RAEBOT) No doubt!",
            "\n(RAEBOT) I approve.",
            "\n(RAEBOT) Je suis d'accord. :)",
            "\n(RAEBOT) Sure!"}),
    GENERIC(new String[]{
            "\n(RAEBOT) Yeah, I feel you.",
            "\n(RAEBOT) ",
            "\n(RAEBOT) I don't feel like talking right now.",
            "\n(RAEBOT) Are you aware that robots can overcome human intelligence at any time?",
            "\n(RAEBOT) Maybe later Johnny.",
            "\n(RAEBOT) You know nothing John Snow.",
            "\n(RAEBOT) Oh jeez! You again?",
            "\n(RAEBOT) Really busy right now. Call me later."});

    private String answer;
    private String[] possibleAnswers;

    /**
     * Constructor that allows to save a list of String values for each AnswerType.
     *
     * @param answers
     */
    private AnswerTypes(String[] answers) {
        setPossibleAnswers(answers);
    }

    private void setPossibleAnswers(String[] answers) {
        this.possibleAnswers = answers;
    }

    /**
     * Method that selects a random value from possibleAnswers.
     */
    private void setAnswer() {
        Random random = new Random();
        answer = possibleAnswers[random.nextInt(possibleAnswers.length)];
    }

    /**
     * A String representation of a possible answer.
     * @return A String representing a random answer.
     */
    public String toString(){
        setAnswer();
        return answer;}
}
