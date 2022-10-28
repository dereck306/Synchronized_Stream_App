package com.example.Dao;

import DTO.ListaReproduccion;
import DTO.ListaReproduccionUsuario;
import DTO.Usuarios;
import baseDatos.Conexion;
import com.example.Controlador.Controlador_Identificacion;
import com.example.Controlador.Controlador_ListaUsuarios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListaReproduccionDAO {

    Conexion conexion = new Conexion();
    Connection connection = conexion.getConnection();
    String hola = Controlador_Identificacion.hola.toLowerCase();



    public ArrayList<ListaReproduccion> metodoBuscarlista(){
        ArrayList<ListaReproduccion> filtroListas = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ListaReproduccion");
            while (resultSet.next()) {
                ListaReproduccion lista = new ListaReproduccion(
                        resultSet.getString("nombre"),
                        resultSet.getString("tema"),
                        resultSet.getString("fecha"),
                        resultSet.getInt("Usuario_identificacion"));


                filtroListas.add(lista);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroListas;
    }

    public ArrayList<ListaReproduccionUsuario> metodoBuscarlistaUsuario() {
        ArrayList<ListaReproduccionUsuario> filtroListasUsuario = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ListaReproduccion where Usuario_identificacion ="+ hola);

            while (resultSet.next()) {
                ListaReproduccionUsuario lista = new ListaReproduccionUsuario(
                        resultSet.getString("nombre"),
                        resultSet.getString("tema"),
                        resultSet.getString("fecha"),
                        resultSet.getInt("Usuario_identificacion"));


                filtroListasUsuario.add(lista);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroListasUsuario;
    }


    public ArrayList<ListaReproduccionUsuario> metodoBuscarlistaUsuarioUltimas() {
        ArrayList<ListaReproduccionUsuario> filtroListasUsuario = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ListaReproduccion Where Usuario_identificacion ="+"'"+hola+"'"+"order by visto DESC");

            while (resultSet.next()) {
                ListaReproduccionUsuario lista = new ListaReproduccionUsuario(
                        resultSet.getString("nombre"),
                        resultSet.getString("tema"),
                        resultSet.getString("fecha"),
                        resultSet.getInt("Usuario_identificacion"));


                filtroListasUsuario.add(lista);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroListasUsuario;
    }
}

