module com.cesur.aplicaciondual {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires jakarta.persistence;
    requires java.naming;

    opens com.cesur.aplicaciondual.domain.entities.alumno;
    opens com.cesur.aplicaciondual.domain.entities.empresa;
    opens com.cesur.aplicaciondual.domain.entities.profesor;
    opens com.cesur.aplicaciondual.domain.entities.actividad;
    opens com.cesur.aplicaciondual to javafx.fxml;
    exports com.cesur.aplicaciondual;
    exports com.cesur.aplicaciondual.controllers;
    opens com.cesur.aplicaciondual.controllers to javafx.fxml;
}