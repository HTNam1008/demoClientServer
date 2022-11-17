package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    private Button openServerButton;

    private Server server;
    public void setOpenServerButton(ActionEvent e) throws IOException {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        server=new Server(new ServerSocket(1234));

        alert.setContentText("Server is opened !");
        alert.setTitle("Sever");
        alert.show();
    }

    public void setExitButton(ActionEvent e) {
        Stage stage=(Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*try {
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error open server !!!");
        }*/
    }
}