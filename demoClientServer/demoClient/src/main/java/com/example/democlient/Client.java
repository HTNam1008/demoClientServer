package com.example.democlient;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client {


    private static Socket socket;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    public Client(Socket socket) throws IOException {
        Client.socket =socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public static void sendFunction(String nameFunction) throws IOException {
        bufferedWriter.write(nameFunction);
    }

    public static void readProcess() throws IOException {

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

        String s=bufferedReader.readLine();
        System.out.println(s);
        root.setCenter(tableView);

        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
