package com.example.Controlador;

import DTO.ListaReproduccionUsuario;
import DTO.Usuarios;
import DTO.VideoLista;
import com.example.Dao.ListaReproduccionDAO;
import com.example.Dao.VideosListaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controlador_VideoLista implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button buttonBack;

    private ImageView ivback;

    @FXML
    private TableColumn nombrevideo;
    @FXML
    private TableColumn path;
    @FXML
    private TableView tabla;




    private ObservableList<VideoLista> videoLista;

    public void switchToScenelistaReproduccion(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Listar-Usuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1000,1000);
        stage.setScene(scene);
        stage.show();

    }

    public void Siguiente(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Ip.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }



    public void switchToSceneAgregarVideoLista(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Agregar-VideoLista2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneMediaPlayerLista(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Media-Player-Host-Lista.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        VideosListaDAO listaReproduccionDAO = new VideosListaDAO();
        ArrayList<VideoLista> Lista_Videos = listaReproduccionDAO.metodoBuscarVideosLista();
        System.out.println(Lista_Videos);

        videoLista = FXCollections.observableArrayList();
        videoLista.setAll(Lista_Videos);

        this.nombrevideo.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.path.setCellValueFactory(new PropertyValueFactory("Path"));


        tabla.setItems(videoLista);


        Image ImageBack = new Image(new File("src/Recursos/exit.png").toURI().toString());
        ivback = new ImageView(ImageBack);
        ivback.setFitWidth(20);
        ivback.setFitHeight(20);

        buttonBack.setGraphic(ivback);


    }
    }

