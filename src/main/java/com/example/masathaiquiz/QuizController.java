package com.example.masathaiquiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4, prevBtn, nextBtn;

//  counter for the current question number
// Counter for the current question number
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    private List<Question> questions;

    public void initialize(){
        // Load questions from the external file
        loadQuestionsFromFile("/com/example/masathaiquiz/question_list.txt");

        // Display the first question
        loadQuestion(currentQuestionIndex);
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

    private void showResults() {
        try {
            // Close the current stage (quiz stage)
            Stage currentStage = (Stage) question.getScene().getWindow();
            currentStage.close();

            // Load the results page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("results.fxml"));
            Parent root = loader.load();

            // Access the controller for the results page
            ResultsController resultsController = loader.getController();

            // Set the results data
            resultsController.setResults(getCorrectAnswers(), getTotalQuestions(), getWrongAnswers(), getPercentageCorrect());

            // Show the results page
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Results!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            Question currentQuestion = questions.get(index);
            question.setText((index + 1) + ". " + currentQuestion.getQuestionText());
            opt1.setText(currentQuestion.getOptions()[0]);
            opt2.setText(currentQuestion.getOptions()[1]);
            opt3.setText(currentQuestion.getOptions()[2]);
            opt4.setText(currentQuestion.getOptions()[3]);

            // Enable/disable next/previous buttons based on the current index
            prevBtn.setDisable(index == 0);
            nextBtn.setDisable(index == questions.size() - 1);
        }
    }

    private void checkAnswer(String selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String correctOption = currentQuestion.getFullCorrectOption();

        if (selectedOption.equals(correctOption)) {
//            System.out.println("This is the print statement");
            correctAnswers++;
        } else {
            wrongAnswers++;
        }
    }

    @FXML
    public void prevBtnClicked(ActionEvent event) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            loadQuestion(currentQuestionIndex);
        }
    }

    @FXML
    public void nextBtnClicked(ActionEvent event) {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            loadQuestion(currentQuestionIndex);
        }
    }

    @FXML
    public void opt1Clicked(ActionEvent event){
        checkAnswer(opt1.getText());
        loadNextQuestion();
    }

    @FXML
    public void opt2Clicked(ActionEvent event){
        checkAnswer(opt2.getText());
        loadNextQuestion();
    }

    @FXML
    public void opt3Clicked(ActionEvent event){
        checkAnswer(opt3.getText());
        loadNextQuestion();
    }

    @FXML
    public void opt4Clicked(ActionEvent event){
        checkAnswer(opt4.getText());
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            loadQuestion(currentQuestionIndex);
        } else {
            showResults();
        }
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public double getPercentageCorrect() {
        int totalQuestions = correctAnswers + wrongAnswers;
        if (totalQuestions == 0) {
            return 0.0;
        }
        double percentage = ((double) correctAnswers / totalQuestions) * 100;

        // Format to display only one decimal place
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return Double.parseDouble(decimalFormat.format(percentage));
    }

}
