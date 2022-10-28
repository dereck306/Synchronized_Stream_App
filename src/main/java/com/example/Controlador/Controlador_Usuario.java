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
import java.util.regex.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controlador_Usuario {

    /** declaracion de variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    private File filePath;
    @FXML
    private Label imagenLBU;

    @FXML
    private TextField txtNombreUsuario;

    @FXML
    private TextField txtApellidosUsuario;

    @FXML
    private TextField txtIdentificacionUsuario;

    @FXML
    private TextField txtNombreDelUsuario;

    @FXML
    private TextField txtContrasennaUsuario;


    Connection con;
    PreparedStatement pst;


    /**
     * Se encarga del cambio de escena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event Recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */


    public void switchToSceneIniciarSesion(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Iniciar-Sesion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,616,400);
        stage.setScene(scene);
        stage.show();

    }

    /** metodo para elegir el path
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event Recibe como parametro la accion realizada por usuario
     * @return
     */

    @FXML
    public String elegirPath(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        this.filePath = fileChooser.showOpenDialog(stage);
        String path = filePath.getAbsolutePath();
        imagenLBU.setText(path);
        path = imagenLBU.getText();

        return path;
    }


    /** agregacion de la contrasena
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event Recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el código
     */


    @FXML
    void agregar(ActionEvent event) throws IOException {

        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,8}$");



        String identificacion = txtIdentificacionUsuario.getText();
        String usuario = txtNombreDelUsuario.getText();
        String contrasena = txtContrasennaUsuario.getText();
        String apellido = txtApellidosUsuario.getText();
        String nombre = txtNombreUsuario.getText();
        String avatar = imagenLBU.getText();

        Matcher m = p.matcher(contrasena);
        boolean result  = m.matches();


        if(identificacion == "" || usuario == "" || contrasena == "" || apellido == "" || nombre == ""){


            root = FXMLLoader.load(getClass().getResource("Pagina-ErrorRegistro.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,616,400);
            stage.setScene(scene);
            stage.show();

        } else if (result) {
            System.out.println("Hola");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas","root","root");
                pst = con.prepareStatement("Insert into Peliculas.Usuario(identificacion,usuario,contrasena,apellido,nombre,avatar)values(?,?,?,?,?,?)");
                pst.setString(1,identificacion);
                pst.setString(2,usuario);
                pst.setString(3,contrasena);
                pst.setString(4,apellido);
                pst.setString(5,nombre);
                pst.setString(6,avatar);
                int status = pst.executeUpdate();




            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas","root","root");
                pst = con.prepareStatement("UPDATE Peliculas.Usuario SET tipousuario = 'Usuario' WHERE usuario = ?");
                pst.setString(1,usuario);
                int status = pst.executeUpdate();



                root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root,616,400);
                stage.setScene(scene);
                stage.show();


            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }


        }else{

            root = FXMLLoader.load(getClass().getResource("Pagina-ErrorRegistro.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,616,400);
            stage.setScene(scene);
            stage.show();
        }

}}
