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

    public String getFullCorrectOption() {
        return getOptionText(correctOption);
    }

    private String getOptionText(String option) {
        switch (option) {
            case "A":
                return options[0];
            case "B":
                return options[1];
            case "C":
                return options[2];
            case "D":
                return options[3];
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }

    public String[] getOptions() {
        return options;
    }
}

