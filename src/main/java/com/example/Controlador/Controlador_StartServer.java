package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controlador_StartServer {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void StartServer(ActionEvent event) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket ss = serverSocket.accept();
            DataOutputStream dout = new DataOutputStream(ss.getOutputStream());
            dout.writeUTF(Controlador_Buscar.data);
            dout.flush();
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root = FXMLLoader.load(getClass().getResource("Media-Player-Host.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneMediaPlayerHost(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Media-Player-Host.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }



    }




