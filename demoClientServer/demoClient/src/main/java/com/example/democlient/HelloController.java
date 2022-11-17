package com.example.democlient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;



public class HelloController implements Initializable {
    @FXML
    private Label textConnectServer;

    @FXML
    private Button processButton;

    @FXML
    protected void onConnectButton() throws IOException {
        client =new Client(new Socket("192.168.1.12",1234));

        textConnectServer.setText("Server Connected !!!");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clientConnect.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage stage=new Stage();
        stage.setTitle("Remote control");
        stage.setScene(scene);
        stage.show();

    }

    public void onProcessButton(ActionEvent event) throws InterruptedException, IOException {
            //String s="PROCESS";
            //Client.sendFunction(s);
            // Client.readProcess();
        Client.readProcess();
            Stage primaryStage=new Stage();
            primaryStage.setTitle("listview");
            BorderPane root=new BorderPane();
            TableView<User> tableView=new TableView<User>();

            TableColumn<User,String> nameProcess=new TableColumn<User, String>("Name Process");
            nameProcess.setCellValueFactory(new PropertyValueFactory<User,String>("nameProcess"));

            TableColumn<User,String> pid=new TableColumn<User, String>("PID");
            pid.setCellValueFactory(new PropertyValueFactory<User,String>("pid"));

            tableView.getColumns().add(nameProcess);
            tableView.getColumns().add(pid);

            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


            /*Process process = new ProcessBuilder("tasklist.exe", "/fo", "csv","/nh").start();

            new Thread(() -> {
                Scanner sc = new Scanner(process.getInputStream());
                if (sc.hasNextLine()) sc.nextLine();
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(",");
                    String unq = parts[0].substring(1).replaceFirst(".$", "");
                    String pid1 = parts[1].substring(1).replaceFirst(".$", "");
                    //System.out.println(unq + " " + pid+" ");
                    tableView.getItems().add(new User(unq,pid1));
                }
            }).start();
            process.waitFor();
            System.out.println("Done");*/

            root.setCenter(tableView);

            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    private Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}