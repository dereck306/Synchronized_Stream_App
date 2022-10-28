package com.example.Controlador;

import DTO.ListaReproduccionUsuario;
import DTO.Usuarios;
import DTO.VideoLista;
import com.example.Dao.UsuariosDAO;
import com.example.Dao.ListaReproduccionDAO;
import com.example.Dao.VideosListaDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {

    /**declaracion del escenario principal
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param stage Recibe como parametro la accion realizada por usuario
     * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Iniciar-Sesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();

    }
}