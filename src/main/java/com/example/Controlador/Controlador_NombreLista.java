package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controlador_NombreLista {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Connection con;
    PreparedStatement pst;

    @FXML
    private TextField nombre;

    public static String nombreLista;

    public void switchToSceneListausuarios(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Listar-Usuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneVideosLista(ActionEvent event) throws IOException {
        nombreLista = nombre.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
            pst = con.prepareStatement("UPDATE ListaReproduccion SET visto = visto + 1 WHERE nombre = ?");
            pst.setString(1, nombreLista);
            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        root = FXMLLoader.load(getClass().getResource("Videos-Lista.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    }
}
