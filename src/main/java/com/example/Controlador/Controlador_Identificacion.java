package com.example.Controlador;

import DTO.ListaReproduccionUsuario;
import com.example.Dao.ListaReproduccionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controlador_Identificacion {


    @FXML
    private TextField id;

    private Scene scene;
    private Parent root;
    private Stage stage;


    public static String hola;




    public void switchToScenePaginaPrincipal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneListaUsuario(ActionEvent event) throws IOException {
        hola = id.getText();

        root = FXMLLoader.load(getClass().getResource("Listar-Usuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1000,1000);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneUltimasListasVistas(ActionEvent event) throws IOException {
        hola = id.getText();

        root = FXMLLoader.load(getClass().getResource("Ultimas-Listas-Vistas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }


}
