package com.example.Controlador;

import DTO.Videos;
import com.example.Dao.VideosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controlador_Buscar implements Initializable {

        /**  declararcion de variables
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         */
        private Stage stage;
        private Scene scene;
        private Parent root;
        public static String data;

        @FXML
        private Button buttonBack;

        private ImageView ivback;

        @FXML
        private TableColumn calificacion;

        @FXML
        private TableColumn genero;
        @FXML
        private TableColumn fecha;
        @FXML
        private TableColumn descripcion;
        @FXML
        private TableColumn ruta;

        @FXML
        private TableColumn nombre;

        @FXML
        private TableView tabla;

        @FXML
        private TextField buscarField;

        private ObservableList<Videos> videosBuscar;

        Connection con;
        PreparedStatement pst;

        /**
         * Se encarga del cambio de escena
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         * @param event recibe como parametro la accion realizada por usuario
         * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
         */

        public void switchToScenePaginaPrincipal(ActionEvent event) throws IOException {
                root = FXMLLoader.load(getClass().getResource("Pagina-Principal.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root,750,500);
                stage.setScene(scene);
                stage.show();

        }

        public void switchToSceneStartHost(ActionEvent event) throws IOException {

                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
                        pst = con.prepareStatement("UPDATE Videos SET visto = visto + 1 WHERE path = ?");
                        pst.setString(1, data);
                        int status = pst.executeUpdate();


                } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                }

                root = FXMLLoader.load(getClass().getResource("IPHOSTVIDEO.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root,750,500);
                stage.setScene(scene);
                stage.show();

        }



        /**
         * Se encarga del cambio de escena
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         * @param event recibe como parametro la accion realizada por usuario
         * @throws IOException Captura las excepciones que se hayan podido reproducir en el codigo
         */

        public void switchToSceneMediaPlayerHost(ActionEvent event) throws IOException {
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
                        pst = con.prepareStatement("UPDATE Videos SET visto = visto + 1 WHERE path = ?");
                        pst.setString(1, data);
                        int status = pst.executeUpdate();


                } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                }

                root = FXMLLoader.load(getClass().getResource("Media-Player-Host.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root,750,500);
                stage.setScene(scene);
                stage.show();
        }



        /**
         * La funcion se encarga de rellenar el TableView y seleccionar la ruta deseada
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         * @param url recibe como parametro la accion realizada por usuario
         * @param resourceBundle recibe como parametro la accion realizada por usuario
         */


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                VideosDAO dao = new VideosDAO();
                ArrayList<Videos> videos = dao.metodoBuscar();

                videosBuscar = FXCollections.observableArrayList();
                videosBuscar.setAll(videos);

                this.nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                this.genero.setCellValueFactory(new PropertyValueFactory("genero"));
                this.fecha.setCellValueFactory(new PropertyValueFactory("fecha"));
                this.descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                this.calificacion.setCellValueFactory(new PropertyValueFactory("calificacion"));
                this.ruta.setCellValueFactory(new PropertyValueFactory("path"));

                tabla.setItems(videosBuscar);

                tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                tabla.getSelectionModel().setCellSelectionEnabled(true);


                FilteredList<Videos> buscardatos = new FilteredList<>(videosBuscar,b -> true);
                buscarField.textProperty().addListener((observable, oldValue, newValue) -> {
                buscardatos.setPredicate(videosBuscar -> {
                        if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                                return true;
                        }

                        String searchKeyword = newValue.toLowerCase();

                        if(videosBuscar.getNombre().toLowerCase().indexOf(searchKeyword) > -1) {
                                return true;
                        } else if (videosBuscar.getGenero().toLowerCase().indexOf(searchKeyword) > -1) {
                                return true;
                        }else{
                                return false;
                        }

                });

                });

                SortedList<Videos> sortedData = new SortedList<>(buscardatos);
                sortedData.comparatorProperty().bind(tabla.comparatorProperty());
                tabla.setItems(sortedData);


                Image ImageBack = new Image(new File("src/Recursos/exit.png").toURI().toString());
                ivback = new ImageView(ImageBack);
                ivback.setFitWidth(20);
                ivback.setFitHeight(20);

                buttonBack.setGraphic(ivback);



        }

        /**
         * @author Dereck Rojas, Emilio Valverde, Karen Porras
         * @param mouseEvent recibe como parametro la accion realizada por usuario
         * @throws IOException Captura las excepciones que se hayan podido reproducir en el código
         */

        public void columnaSeleccionada(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
                String path = "";
                TablePosition tablePosition = (TablePosition) tabla.getSelectionModel().getSelectedCells().get(0);
                int row=tablePosition.getRow();
                Videos item = (Videos) tabla.getItems().get(row);
                TableColumn tableColumn= tablePosition.getTableColumn();
                data = (String) tableColumn.getCellObservableValue(item).getValue();


        }
}



