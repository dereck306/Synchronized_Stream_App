package com.example.Controlador;

import DTO.Usuarios;
import com.example.Dao.UsuariosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Controlador_IniciarSesion {



    /** declaracion de variables
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TextField txtnombreUsuario;

    @FXML
    private TextField txtcontraUsuario;




    Connection con;
    PreparedStatement pst;

    /** cambio de escenario
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event Recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el código
     */
    public void switchToSceneRegistro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Registar-Usuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }






    /** ingreso con nombre de usuario y contrasenna
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param event Recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el código
     */
    @FXML
    void ingresar(ActionEvent event) throws IOException {

        UsuariosDAO usuariosDAO = new UsuariosDAO();
        ArrayList<Usuarios> usuarios = usuariosDAO.metodoBuscarUsuarios();
        

         String username =  txtnombreUsuario.getText();
        String contra = txtcontraUsuario.getText();
        int id;

        System.out.println(usuarios);

        for (Usuarios i: usuarios){
                if (i.getUsuario().equals(username)){
                    if (i.getContrasena().equals(contra)){


                        root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root,750,500);
                        stage.setScene(scene);
                        stage.show();
                    }else{

                        root = FXMLLoader.load(getClass().getResource("Pagina-ErrorLogin.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root,616,400);
                        stage.setScene(scene);
                        stage.show();
                    }
                }else{

                    root = FXMLLoader.load(getClass().getResource("Pagina-ErrorLogin.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root,616,400);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }







