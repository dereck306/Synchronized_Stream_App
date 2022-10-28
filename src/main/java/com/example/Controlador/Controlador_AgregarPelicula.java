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
import java.util.ResourceBundle;


public class Controlador_AgregarPelicula implements Initializable {

    /** declaracion de variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */

    @FXML
    private Button btnAgregar;

    @FXML
    private Button buttonBack;

    private ImageView ivBack;

    @FXML
    private TextField txtGenero;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtDescripcion;

    @FXML
    private Label lblFile;

    private FileChooser fileChooser;
    private File filePath;


    public static String nombre1;

    public static String Path1;



    Connection con;
    PreparedStatement pst;


    /**
     *  Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */

    public void switchToScenePaginaPrincipal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,403,314);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Se encarga de eligir y guardar la ruta del archivo seleccionado
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por usuario
     * @return Captura las excepciones que se hayan podido reproducir en el codigo
     */
        @FXML
        public String elegirPath(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();

        this.filePath = fileChooser.showOpenDialog(stage);
        String path = filePath.getAbsolutePath();

        lblFile.setText(path);
        path = lblFile.getText();

        return path;
    }

    /**
     * Agrega los datos de el Video que el usuario quiera agregar a la base de datos
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event recibe como parametro la accion realizada por el usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */
    @FXML
    void agregar(ActionEvent event) throws IOException{

        String nombre = txtNombre.getText();
        nombre1 = nombre;
        String genero = txtGenero.getText();
        String fecha = txtFecha.getText();
        String descripcion = txtDescripcion.getText();
        String path = lblFile.getText();
        Path1 = path;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas","root","root");
            pst = con.prepareStatement("Insert into Peliculas.Videos(nombre,genero,fecha,descripcion,path)values(?,?,?,?,?)");
            pst.setString(1,nombre);
            pst.setString(2,genero);
            pst.setString(3,fecha);
            pst.setString(4,descripcion);
            pst.setString(5,path);
            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas","root","root");
            pst = con.prepareStatement("UPDATE Videos SET calificacion = 0 WHERE nombre = ?");
            pst.setString(1,nombre);
            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas","root","root");
            pst = con.prepareStatement("UPDATE Videos SET visto = 0 WHERE nombre = ?");
            pst.setString(1,nombre);
            int status = pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



        root = FXMLLoader.load(getClass().getResource("Agregar-Lista-Reproduccion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,300,161);
        stage.setScene(scene);
        stage.show();


    }





    /**
     *  Pone una imagen en el Boton "buttonBack"
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param url recibe como parametro la accion realizada por el usuario
     * @param resourceBundle recibe como parametro la accion realizada por el usuario
     */



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image imageBack = new Image(new File("src/Recursos/exit.png").toURI().toString());
        ivBack = new ImageView(imageBack);
        ivBack.setFitWidth(20);
        ivBack.setFitHeight(20);

        buttonBack.setGraphic(ivBack);

    }
}
