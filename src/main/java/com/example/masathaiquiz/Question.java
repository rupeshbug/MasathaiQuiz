package com.example.masathaiquiz;

public class Question {
    private String questionText;
    private String correctOption;
    private String[] options;

    public Question(String questionText, String correctOption, String[] options) {
        this.questionText = questionText;
        this.correctOption = correctOption;
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public String[] getOptions() {
        return options;
    }
}

