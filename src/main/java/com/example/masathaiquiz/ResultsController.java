package com.example.masathaiquiz;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;

public class ResultsController {

    @FXML
    private Label resultLabel;

    @FXML
    private Label marksObtained;

    @FXML
    private Label resultAnnouncement;

    @FXML
    private PieChart pieChart;

    // New fields to store results
    private int correctAnswers;
    private int totalQuestions;
    private int wrongAnswers;
    private double percentageCorrect;

    // Method to set results
    public void setResults(int correctAnswers, int totalQuestions, int wrongAnswers, double percentageCorrect) {
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.wrongAnswers = wrongAnswers;
        this.percentageCorrect = percentageCorrect;

        // Call a method to display results
        Platform.runLater(this::displayResults);
    }

    // Method to display results
    private void displayResults() {
        // Use the results to update UI components
        resultLabel.setText("Results:\n" +
                "Total Questions: " + totalQuestions + "\n" +
                "Correct Answers: " + correctAnswers + "\n" +
                "Wrong Answers: " + wrongAnswers + "\n" +
                "Percentage Correct: " + percentageCorrect + "%");

        marksObtained.setText(correctAnswers + "/" + totalQuestions);

        if(correctAnswers >= 10){
            resultAnnouncement.setText("CONGRATULATIONS! You have passed the citizenship test.");
        } else {
            resultAnnouncement.setText("SORRY! You have failed the citizenship test.");
        }

        updatePieChartData();
    }

    private void updatePieChartData() {
        pieChart.getData().clear();

        // Add data points
        pieChart.getData().add(new Data("Correct Answers", correctAnswers));
        pieChart.getData().add(new Data("Wrong Answers", wrongAnswers));
    }

}
