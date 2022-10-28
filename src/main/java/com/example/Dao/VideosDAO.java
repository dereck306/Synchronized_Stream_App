package com.example.Dao;

import DTO.Videos;
import baseDatos.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Dereck Rojas, Emilio Valverde, Karen Porras
 */
public class VideosDAO {
    Conexion conexion = new Conexion();
    Connection connection = conexion.getConnection();

    //Se Crean todos los metodos que van a hacer una comunicacion con la base de datos


    //Se crea arraylist para guardar los resultados de la base de datos en el modelo Videos


    /** metodo para buscar donde se ejecuta el query
     * Esta funcion se encarga de agregar los datos de la base de datos y mapearlos en el model
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @return Retorna un Arraylist de tipo Videos
     */

    public ArrayList<Videos> metodoBuscar(){
        ArrayList<Videos> filtroBusqueda = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VIDEOS");
            while (resultSet.next()) {
                Videos videos = new Videos(resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getString("genero"),
                resultSet.getString("fecha"),
                resultSet.getString("descripcion"),
                resultSet.getInt("calificacion"),
                        resultSet.getString("path"));

                filtroBusqueda.add(videos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroBusqueda;
    }

    public ArrayList<Videos> metodoBuscarUltimosVideos(){
        ArrayList<Videos> filtroBusqueda = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Videos order by visto DESC");
            while (resultSet.next()) {
                Videos videos = new Videos(resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("genero"),
                        resultSet.getString("fecha"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("calificacion"),
                        resultSet.getString("path"));

                filtroBusqueda.add(videos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroBusqueda;
    }

}
