package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public Connection connection;
    private static final String driver = "com.mysql.jdbc.Driver";

    public Conexion(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //Se crea el objeto conexion el cual es el que realiza la conexion con la base de datos

    /**
     * Establece la Conexion con la base de datos
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     */

    public Conexion() {
        connection = null;

        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Peliculas", "root", "root");
            if (connection != null) {
                System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion " + e);
        }
    }
}
