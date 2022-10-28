package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controlador_AgregarVideoLista2 {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Connection con;
    PreparedStatement pst;

    @FXML
    private Label lblFile;

    private FileChooser fileChooser;
    private File filePath;

    @FXML
    private TextField nombreVideo;

    String id = Controlador_Identificacion.hola;

    String nombreLista1 = Controlador_NombreLista.nombreLista;

    public void switchToScenelistaReproduccion(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Listar-Usuario.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public String elegirPath(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();

        this.filePath = fileChooser.showOpenDialog(stage);
        String path = filePath.getAbsolutePath();

        lblFile.setText(path);
        path = lblFile.getText();

        return path;
    }

    @FXML
    void agregarVideoLista(ActionEvent event) throws IOException {
        String nombreVideo1 = nombreVideo.getText();
        String path = lblFile.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
            pst = con.prepareStatement("Insert into Peliculas.VideosListas(nombre,Path,identificacion,ListaReproduccion_nombre)values(?,?,?,?)");
            pst.setString(1, nombreVideo1);
            pst.setString(2, path);
            pst.setString(3, id);
            pst.setString(4, nombreLista1);
            int status = pst.executeUpdate();

            root = FXMLLoader.load(getClass().getResource("Listar-Usuario.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 403, 314);
            stage.setScene(scene);
            stage.show();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}