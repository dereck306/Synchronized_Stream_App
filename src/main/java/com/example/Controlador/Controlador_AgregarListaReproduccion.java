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


public class Controlador_AgregarListaReproduccion {

    /** delcaracion de variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    Connection con;
    PreparedStatement pst;

    @FXML
    private TextField id;
    @FXML
    private TextField nombreLista;

    String nombre = Controlador_AgregarPelicula.nombre1;

    String Path = Controlador_AgregarPelicula.Path1;

    public void switchToSceneAgregarVideo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Agregar-Pelicula.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,403,314);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */

    @FXML
    void agregarVideoLista(ActionEvent event ) throws IOException {
        String  id1 = id.getText();
        String nombreLista1 = nombreLista.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
            pst = con.prepareStatement("Insert into Peliculas.VideosListas(nombre,Path,identificacion,ListaReproduccion_nombre)values(?,?,?,?)");
            pst.setString(1, nombre);
            pst.setString(2, Path);
            pst.setString(3, id1);
            pst.setString(4, nombreLista1);
            int status = pst.executeUpdate();

            root = FXMLLoader.load(getClass().getResource("Agregar-Pelicula.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,403,314);
            stage.setScene(scene);
            stage.show();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }












    }
