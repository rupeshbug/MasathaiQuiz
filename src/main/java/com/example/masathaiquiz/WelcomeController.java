package com.example.masathaiquiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private Label welcomeUser;
    @FXML
    private Button logoutBtn;
    @FXML
    private ImageView flagImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml", "Login!", null, null);
            }
        });

    }

    public void setUserInformation(String username, String nationality) {
        welcomeUser.setText("Welcome " + username + "!!!");
        displayFlag(nationality);
    }

    private void displayFlag(String nationality) {
        String imagePath = "/com/example/masathaiquiz/images/";

        // Set the path to the appropriate image based on nationality
        switch (nationality) {
            case "Malaysia":
                imagePath += "malaysia.jpg";
                break;
            case "Singapore":
                imagePath += "singapore.jpg";
                break;
            case "Thailand":
                imagePath += "thailand.jpg";
                break;
            default:
                // Handle the default case (e.g., no image)
                break;
        }
        Image flagImage = new Image(getClass().getResource(imagePath).toExternalForm());
        flagImageView.setImage(flagImage);
    }

}
