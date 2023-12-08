package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditAndShowAlumnoController implements Initializable {
    @javafx.fxml.FXML
    private Circle marcoImagen;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private TextField txtHorasDual;
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
    @javafx.fxml.FXML
    private TextField txtTelefono;
    @javafx.fxml.FXML
    private TextField txtApellidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image foto = new Image("img/usuario.png", false);
        marcoImagen.setFill(new ImagePattern(foto));
        comboEmpresa.setConverter(new StringConverter<Empresa>() {
            @Override
            public String toString(Empresa empresa) {
                return (empresa != null) ? empresa.getNombre() : "";
            }

            @Override
            public Empresa fromString(String s) {
                return null;
            }
        });

        var empresaDAO = new EmpresaDAOImp();
        ObservableList<Empresa> empresas = FXCollections.observableArrayList(empresaDAO.getAll());
        comboEmpresa.setItems(empresas);

        txtDni.textProperty().addListener((observable, oldValue, newValue) -> {
            txtDni.setText(newValue.toUpperCase());
        });

        if (Session.getAlumno() != null) {
            txtNombre.setText(Session.getAlumno().getNombre());
            txtApellidos.setText(Session.getAlumno().getApellidos());
            txtTelefono.setText(Session.getAlumno().getTelefono());
            txtDni.setText(Session.getAlumno().getDni());
            txtEmail.setText(Session.getAlumno().getEmail());
            txtPass.setText(Session.getAlumno().getPass());
            txtHorasFct.setText(Session.getAlumno().getFct() + "");
            txtHorasDual.setText(Session.getAlumno().getDual() + "");
            LocalDate fechaModificada = Session.getAlumno().getFecha_nac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            datePicker.setValue(fechaModificada);
            txtObservacion.setText(Session.getAlumno().getObservaciones());
            comboEmpresa.setValue(Session.getAlumno().getEmpresa());
            txtDni.setEditable(false);
        } else {
            comboEmpresa.setPromptText("Selecciona una empresa");
            datePicker.setValue(LocalDate.now());
        }

    }

    @javafx.fxml.FXML
    public void returnToMain(ActionEvent actionEvent) {
        Session.setAlumno(null);
        App.loadFXML("viewsProfesor/main-view-profesor.fxml");
    }

    @javafx.fxml.FXML
    public void saveAlumno(ActionEvent actionEvent) {
        if (checkFields()) {
            Alumno a = new Alumno();
            a.setObservaciones(txtObservacion.getText());
            a.setPass(txtPass.getText());
            a.setTelefono(txtTelefono.getText());

            var alumnoDAO = new AlumnoDAOImp();

            if (Session.getAlumno() != null) {

            } else {
                //alumnoDAO.save();
            }
        }
    }

    private boolean checkFields() {
        boolean isOk = true;

        if (txtTelefono.getText().length() != 9) {
            isOk = false;
            txtTelefono.setText("");
            txtTelefono.setStyle("-fx-prompt-text-fill: red");
            txtTelefono.setPromptText("Introduce un numero válido");
        }

        String regex = "\\d{8}[A-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txtDni.getText().toUpperCase());

        if (!matcher.matches()) {
            isOk = false;
            txtDni.setText("");
            txtDni.setStyle("-fx-prompt-text-fill: red");
            txtDni.setPromptText("Introduce un DNI válido");
        }

        return isOk;
    }
}