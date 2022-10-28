package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador_MensajeLista {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToSceneAgregarVideo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Agregar-Pelicula.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,403,314);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToSceneAgregarNombreListaID(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Agregar-NombreListaID.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,403,314);
        stage.setScene(scene);
        stage.show();
    }
}
