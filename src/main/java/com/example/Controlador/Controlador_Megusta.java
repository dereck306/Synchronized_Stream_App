package com.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controlador_Megusta implements Initializable {

    /**
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField txtNombre;

    @FXML
    private Button volverLike;

    private ImageView ivback;

    Connection con;
    PreparedStatement pst;

    /**
     * Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */

    public void switchToSceneMediaPlayer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Media-Player-Host.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Se encarga de sumar +1 en la base de datos cuando el usuario pulsa el boton
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por el usuario
     */

    @FXML
    void meGusta(ActionEvent event) {

        String nombre = txtNombre.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
            pst = con.prepareStatement("UPDATE Videos SET calificacion = calificacion + 1 WHERE nombre = ?");
            pst.setString(1, nombre);
            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Se encarga de darle estilo a el boton
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param url recibe como parametro la accion realizada por el usuario
     * @param resourceBundle recibe como parametro la accion realizada por el usuario
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image imageback = new Image(new File("src/Recursos/exit.png").toURI().toString());
        ivback = new ImageView(imageback);
        ivback.setFitWidth(20);
        ivback.setFitHeight(20);

        volverLike.setGraphic(ivback);
    }
}
