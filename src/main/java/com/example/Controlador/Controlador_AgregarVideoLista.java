package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Controlador_AgregarVideoLista implements Initializable {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button buttonBack;

    private ImageView ivBack;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtID;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField txtNombre;

    Connection con;
    PreparedStatement pst;

    public void switchToScenePaginaPrincipal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Listar-Usuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,403,314);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void agregar(ActionEvent event) throws IOException {

        String nombre = txtNombre.getText();
        String tema = txtGenero.getText();
        String fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String Usuario_identificacion = txtID.getText();



        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
            pst = con.prepareStatement("Insert into Peliculas.ListaReproduccion(nombre,tema,fecha,Usuario_identificacion)values(?,?,?,?)");
            pst.setString(1, nombre);
            pst.setString(2, tema);
            pst.setString(3,fecha);
            pst.setString(4,Usuario_identificacion);

            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas","root","root");
            pst = con.prepareStatement("UPDATE Peliculas.ListaReproduccion SET visto = 0 WHERE nombre = ?");
            pst.setString(1,nombre);
            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


            root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,403,314);
            stage.setScene(scene);
            stage.show();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image imageBack = new Image(new File("src/Recursos/exit.png").toURI().toString());
        ivBack = new ImageView(imageBack);
        ivBack.setFitWidth(20);
        ivBack.setFitHeight(20);

        buttonBack.setGraphic(ivBack);
    }
}
