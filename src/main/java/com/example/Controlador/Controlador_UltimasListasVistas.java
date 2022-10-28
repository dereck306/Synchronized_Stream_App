package com.example.Controlador;

import DTO.ListaReproduccionUsuario;
import com.example.Dao.ListaReproduccionDAO;
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

public class Controlador_UltimasListasVistas implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button buttonBack;

    private ImageView ivback;

    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn tema;
    @FXML
    private TableColumn fecha;

    @FXML
    private TableView tabla;

    private ObservableList<ListaReproduccionUsuario> listaBuscar;

    public void switchToScenePaginaPrincipal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,750,500);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListaReproduccionDAO listaReproduccionDAO = new ListaReproduccionDAO();
        ArrayList<ListaReproduccionUsuario> Lista_usuarios = listaReproduccionDAO.metodoBuscarlistaUsuarioUltimas();

        listaBuscar = FXCollections.observableArrayList();
        listaBuscar.setAll(Lista_usuarios);

        this.nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tema.setCellValueFactory(new PropertyValueFactory("tema"));
        this.fecha.setCellValueFactory(new PropertyValueFactory("fecha"));

        tabla.setItems(listaBuscar);

        Image ImageBack = new Image(new File("src/Recursos/exit.png").toURI().toString());
        ivback = new ImageView(ImageBack);
        ivback.setFitWidth(20);
        ivback.setFitHeight(20);

        buttonBack.setGraphic(ivback);

    }
}
