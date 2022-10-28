package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controlador_Escena {
    /** declaracion de variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
        private Stage stage;
        private Scene scene;
        private Parent root;

    /**
     * Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */


    public void switchToSceneBuscar(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Buscar-Pelicula.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,616,400);
            stage.setScene(scene);
            stage.show();

        }


    /**
     * Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */

    /**
     * Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */


        public void switchToSceneAgregarPelicula(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("Agregar-Pelicula.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,600,400);
            stage.setScene(scene);
            stage.show();

        }

    public void switchToSceneAgregarUltimosVideosVistos(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Ultimos-Videos-Vistos.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneIdListas(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Id-Ultimas-Listas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneAgregar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Id.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();





    }}