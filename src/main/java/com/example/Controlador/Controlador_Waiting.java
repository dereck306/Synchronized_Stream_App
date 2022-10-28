package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador_Waiting {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToSceneMediaPlayerHost(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Start-Server.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneMediaPlayerHostLista(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Siguiente-Pelicula.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }
}
