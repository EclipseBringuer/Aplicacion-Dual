package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditAndShowAlumnoController implements Initializable {
    @javafx.fxml.FXML
    private Circle marcoImagen;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtApellido2;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private TextField txtHorasDual;
    @javafx.fxml.FXML
    private TextField txtApellido1;
    @javafx.fxml.FXML
    private TextField txtDni;
    @javafx.fxml.FXML
    private TextField txtPass;
    @javafx.fxml.FXML
    private ComboBox<Empresa> comboEmpresa;
    @javafx.fxml.FXML
    private TextField txtHorasFct;
    @javafx.fxml.FXML
    private TextArea txtObservacion;
    @javafx.fxml.FXML
    private Button btnGuardar;
    @javafx.fxml.FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image foto = new Image("img/usuario.png", false);
        marcoImagen.setFill(new ImagePattern(foto));
        if (Session.getAlumno() != null) {
            txtNombre.setText(Session.getAlumno().getNombre());
            String[] apellidos = Session.getAlumno().getApellidos().split(" ");
            txtApellido1.setText(apellidos[0]);
            txtApellido2.setText(apellidos[1]);
            txtDni.setText(Session.getAlumno().getDni());
            txtEmail.setText(Session.getAlumno().getEmail());
            txtPass.setText(Session.getAlumno().getPass());
            txtHorasFct.setText(Session.getAlumno().getFct() + "");
            txtHorasDual.setText(Session.getAlumno().getDual() + "");
            LocalDate fechaModificada = Session.getAlumno().getFecha_nac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            datePicker.setValue(fechaModificada);
            txtObservacion.setText(Session.getAlumno().getObservaciones());
        }


    }
}