package com.example.masathaiquiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String nationality){
        Parent root = null;
        if(username != null && nationality != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                WelcomeController welcomeController = loader.getController();
                welcomeController.setUserInformation(username, nationality);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void signupUser(ActionEvent event, String name, String username, String password, String dob, String gender, String nationality){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/masathaiquiz", "root", "mysql123");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            result = psCheckUserExists.executeQuery();

            if(result.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This username is already taken.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (name, username, password, dob, gender, nationality) VALUES (?,?,?,?,?,?)");
                psInsert.setString(1, name);
                psInsert.setString(2, username);
                psInsert.setString(3, password);
                psInsert.setString(4, dob);
                psInsert.setString(5, gender);
                psInsert.setString(6, nationality);
                psInsert.executeUpdate();
                changeScene(event, "welcome.fxml", "Welcome!", username, nationality);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loginUser(ActionEvent event, String username, String password, String nationality){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/masathaiquiz", "root", "mysql123");
            preparedStatement = connection.prepareStatement("SELECT password, nationality FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            result = preparedStatement.executeQuery();

            if (!result.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                result.next(); // Move the cursor to the first row.
                String retrievedPassword = result.getString("password");
                String retrievedNationality = result.getString("nationality");

                if (retrievedPassword.equals(password)) {
                    changeScene(event, "welcome.fxml", "Welcome", username, retrievedNationality);
                } else {
                    System.out.println("Passwords do not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect!");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
