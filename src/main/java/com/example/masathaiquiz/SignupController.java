package com.example.masathaiquiz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class SignupController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;
    private final String[] gender = new String[]{"Male", "Female", "Others"};

    public SignupController() {
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        this.choiceBox.getItems().addAll(this.gender);
    }
}
