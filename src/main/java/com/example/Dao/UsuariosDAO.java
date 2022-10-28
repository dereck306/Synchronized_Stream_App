package com.example.Dao;

import DTO.Usuarios;
import baseDatos.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuariosDAO {

    Conexion conexion = new Conexion();
    Connection connection = conexion.getConnection();


    public ArrayList<Usuarios> metodoBuscarUsuarios(){
        ArrayList<Usuarios> filtroUsuarios = new ArrayList();

        try {
            //Se ejecuta el query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Usuario");
            while (resultSet.next()) {
               Usuarios videos = new Usuarios(resultSet.getInt("identificacion"),
                        resultSet.getString("usuario"),
                        resultSet.getString("contrasena"),
                        resultSet.getString("apellido"),
                        resultSet.getString("nombre"),
                        resultSet.getString("avatar"));


                filtroUsuarios.add(videos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtroUsuarios;
    }

}
