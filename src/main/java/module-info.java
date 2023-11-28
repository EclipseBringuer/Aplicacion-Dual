module com.cesur.aplicaciondual {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires jakarta.persistence;
    requires java.naming;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;

    opens img;
    opens img.logos;
    opens img.profes;
    opens img.alumnos;
    opens com.cesur.aplicaciondual.domain.entities.alumno;
    opens com.cesur.aplicaciondual.traza;
    opens com.cesur.aplicaciondual.domain.entities.empresa;
    opens com.cesur.aplicaciondual.domain.entities.profesor;
    opens com.cesur.aplicaciondual.domain.entities.actividad;
    opens com.cesur.aplicaciondual to javafx.fxml;
    exports com.cesur.aplicaciondual;
    exports com.cesur.aplicaciondual.controllers;
    opens com.cesur.aplicaciondual.controllers to javafx.fxml;

}