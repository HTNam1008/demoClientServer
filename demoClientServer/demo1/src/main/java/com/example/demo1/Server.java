package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
    private ServerSocket serverSocket;
    private Socket socket;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) {
       try {
           this.serverSocket = serverSocket;

           this.socket = serverSocket.accept();
           if (socket.isConnected()) {
               System.out.println("Connected");
           }
           else
           {
               System.out.println("Not connected");
           }
           this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
       }catch (IOException e) {
           System.out.println("Error creating server: ");
       }
    }
    static String toreturn;
    public static void sendShowProcess() throws InterruptedException, IOException {

        Process process = new ProcessBuilder("tasklist.exe", "/fo", "csv","/nh").start();

        new Thread(() -> {
            Scanner sc = new Scanner(process.getInputStream());
            if (sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String unq = parts[0].substring(1).replaceFirst(".$", "");
                String pid1 = parts[1].substring(1).replaceFirst(".$", "");
                //System.out.println(unq + " " + pid+" ");
                String toreturn1 =  unq + "/" + pid1 + "\n";
                toreturn=toreturn.concat(toreturn1);
                try {
                    bufferedWriter.write(toreturn);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        process.waitFor();
        System.out.println("Done");
    }

    public static void check() throws IOException, InterruptedException {
        if (bufferedReader.readLine()=="PROCESS")
        {
            sendShowProcess();
        }
    }

    /*public static void main(String[] args) throws IOException, InterruptedException {

    }*/
}