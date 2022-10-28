module com.example.proyecto_poofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.media;
    requires java.desktop;


    opens com.example.Controlador to javafx.fxml;
    exports com.example.Controlador;
    opens DTO to javafx.fxml;
    exports DTO;
}