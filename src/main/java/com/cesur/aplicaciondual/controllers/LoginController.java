package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import com.cesur.aplicaciondual.domain.entities.profesor.ProfesorDAOImp;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.net.URI;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label enlace;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUser.setText("Guillen@gmail.com");
        txtPass.setText("franciscogod");
    }

    @FXML
    public void goToCesurWeb(Event event) {
        String url = "https://www.cesurformacion.com/centros";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo abrir el enlace.");
        }
    }

    @FXML
    public void validateUser(ActionEvent actionEvent) {
        String email = txtUser.getText();
        String pass = txtPass.getText();

        if (!Objects.equals(email, "") && !Objects.equals(pass, "")) {
            var profDAO = new ProfesorDAOImp();
            Profesor p = profDAO.getByAccount(email, pass);

            if (p != null) {
                Session.setProfesor(p);
                App.loadFXML("viewsProfesor/main-view-profesor.fxml");

            } else {
                var alumnDAO = new AlumnoDAOImp();
                Alumno a = alumnDAO.getByAccount(email, pass);

                if (a != null) {
                    Session.setAlumno(a);
                    App.loadFXML("viewsAlumno/main-view-alumno.fxml");

                } else {
                    Alert alert = App.makeNewAlert(
                            Alert.AlertType.INFORMATION,
                            "Error al iniciar sesión",
                            "Credenciales incorrectas, no se ha encontrado este usuario",
                            "Pulsa aceptar para volver al formulario");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = App.makeNewAlert(
                    Alert.AlertType.INFORMATION,
                    "Error al iniciar sesión",
                    "Rellena todos los campos!",
                    "Pulsa aceptar para volver al formulario");
            alert.showAndWait();
        }
    }
}


