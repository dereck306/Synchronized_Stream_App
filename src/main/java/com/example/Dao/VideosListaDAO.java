package com.example.Dao;

import DTO.ListaReproduccionUsuario;
import DTO.VideoLista;
import baseDatos.Conexion;
import com.example.Controlador.Controlador_Identificacion;
import com.example.Controlador.Controlador_ListaUsuarios;
import com.example.Controlador.Controlador_NombreLista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VideosListaDAO {

    Conexion conexion = new Conexion();
    Connection connection = conexion.getConnection();



    String nombreLista = Controlador_NombreLista.nombreLista.toLowerCase();

    String hola = Controlador_Identificacion.hola.toLowerCase();

    public ArrayList<VideoLista> metodoBuscarVideosLista() {
        ArrayList<VideoLista> filtroListasVideos = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nombre,Path FROM VideosListas where ListaReproduccion_nombre ="+"'"+nombreLista+"'" + "and identificacion =" +hola);

            while (resultSet.next()) {
                VideoLista lista = new VideoLista(
                        resultSet.getString("nombre"),
                        resultSet.getString("Path"));
                filtroListasVideos.add(lista);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroListasVideos;
    }
}
