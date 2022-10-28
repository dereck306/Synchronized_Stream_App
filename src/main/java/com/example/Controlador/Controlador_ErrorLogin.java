package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador_ErrorLogin {
    /** declaracion de variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event Captura las excepciones que se hayan podido reproducir en el código
     */

    public void switchToSceneIniciarSesion(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Iniciar-Sesion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,616,400);
        stage.setScene(scene);
        stage.show();

    }


}
