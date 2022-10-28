package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador_Siguiente {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private javafx.scene.control.Button cerrar;

    public static int posicion = -1;


    public void Siguiente (ActionEvent event) throws IOException {



        posicion ++;

        root = FXMLLoader.load(getClass().getResource("Media-Player-Host-Lista.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,236,150);
        stage.setScene(scene);
        stage.show();
    }
}
