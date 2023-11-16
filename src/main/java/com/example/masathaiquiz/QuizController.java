package com.example.masathaiquiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4;

//  counter for the current question number
    int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    private List<Question> questions;
    private int currentQuestionIndex;

    public void initialize(){
        // Load questions from the external file
        loadQuestionsFromFile("/com/example/masathaiquiz/question_list.txt");

        // Display the first question
        loadNextQuestion();
    }

    private void loadQuestionsFromFile(String filePath) {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;

        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into question text and options
                String[] parts = line.split(":");
                if (parts.length == 7) {
                    String questionText = parts[2];
                    String correctOption = parts[1];
                    String[] options = new String[]{parts[3], parts[4], parts[5], parts[6]};

                    // Create a Question object and add it to the list
                    Question question = new Question(questionText, correctOption, options);
                    questions.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            question.setText((currentQuestionIndex + 1) + ". " + currentQuestion.getQuestionText());
            opt1.setText(currentQuestion.getOptions()[0]);
            opt2.setText(currentQuestion.getOptions()[1]);
            opt3.setText(currentQuestion.getOptions()[2]);
            opt4.setText(currentQuestion.getOptions()[3]);

            currentQuestionIndex++;
        } else {
            // Quiz finished, show results or navigate to the result page
        }
    }

    @FXML
    public void opt1Clicked(ActionEvent event){
        loadNextQuestion();
    }

    @FXML
    public void opt2Clicked(ActionEvent event){
        loadNextQuestion();
    }

    @FXML
    public void opt3Clicked(ActionEvent event){
        loadNextQuestion();
    }

    @FXML
    public void opt4Clicked(ActionEvent event){
        loadNextQuestion();
    }

}
