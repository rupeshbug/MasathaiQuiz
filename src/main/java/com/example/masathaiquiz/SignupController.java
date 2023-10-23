package com.example.masathaiquiz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class SignupController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> choiceBox1;
    private final String[] gender = new String[]{"Male", "Female", "Other"};
    private final String [] nationality = new String[]{"Malaysia", "Singapore", "Thailand"};

    @FXML
    private Button signupBtn;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private DatePicker dob;

    public void initialize(URL arg0, ResourceBundle arg1) {

        this.choiceBox.getItems().addAll(this.gender);
        this.choiceBox1.getItems().addAll(this.nationality);

        signupBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_name.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DBUtils.signupUser(event, tf_name.getText(), tf_username.getText(), tf_password.getText(), String.valueOf(dob.getValue()), choiceBox.getValue(), choiceBox1.getValue());
                } else {
                    System.out.println("Please fill in all information.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });
    }
}
