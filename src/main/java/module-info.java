module com.cesur.aplicaciondual {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;


    opens com.cesur.aplicaciondual to javafx.fxml;
    exports com.cesur.aplicaciondual;
    exports com.cesur.aplicaciondual.controllers;
    opens com.cesur.aplicaciondual.controllers to javafx.fxml;
}