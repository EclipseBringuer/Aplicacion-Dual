package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.actividad.ActividadDAOImp;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
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
    @javafx.fxml.FXML
    private Button btnActividades;
    @javafx.fxml.FXML
    private Label labelAlumno;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelAlumno.setText(Session.getAlumno().getNombre() + " " + Session.getAlumno().getApellidos());
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
        Empresa e = new Empresa();
        e.setNombre("Ninguna");
        empresas.add(e);
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
            txtHorasDual.setText("0");
            txtHorasFct.setText("0");
            btnActividades.setVisible(false);
            btnActividades.setManaged(false);
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
            a.setDual(Integer.parseInt(txtHorasDual.getText()));
            a.setProfesor(Session.getProfesor());
            a.setFct(Integer.parseInt(txtHorasFct.getText()));
            a.setApellidos(txtApellidos.getText());
            a.setFecha_nac(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            a.setEmail(txtEmail.getText());
            a.setEmpresa(comboEmpresa.getSelectionModel().getSelectedItem());
            a.setNombre(txtNombre.getText());
            a.setDni(txtDni.getText());

            var alumnoDAO = new AlumnoDAOImp();

            if (Session.getAlumno() != null) {
                Alumno.merge(a, Session.getAlumno());
                alumnoDAO.update(Session.getAlumno());
            } else {
                alumnoDAO.save(a);
            }

            App.makeNewAlert(Alert.AlertType.INFORMATION,
                    "Guardado",
                    "Alumno " + a.getNombre() + " guardado con éxito",
                    "Pulsa aceptar para salir").showAndWait();

            Session.getProfesor().getAlumnos().add(a);

            returnToMain(actionEvent);
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

        if (Objects.equals(txtPass.getText(), "") || txtPass.getText() == null) {
            isOk = false;
            txtPass.setText("");
            txtPass.setStyle("-fx-prompt-text-fill: red");
            txtPass.setPromptText("Introduce una contraseña válida");
        }

        if (!txtEmail.getText().contains("@")) {
            isOk = false;
            txtEmail.setText("");
            txtEmail.setStyle("-fx-prompt-text-fill: red");
            txtEmail.setPromptText("Introduce un correo válido");
        }
        isOk = validarYGuardarHoras(txtHorasDual, isOk);
        isOk = validarYGuardarHoras(txtHorasFct, isOk);

        return isOk;
    }

    private boolean validarYGuardarHoras(TextField textField, boolean isOk) {
        String newValue = textField.getText();
        try {
            int horas = Integer.parseInt(newValue);

            // Verificar si está en el rango permitido
            if (horas < 0 || horas > 600) {
                textField.setText(""); // Limpiar el campo en caso de un valor incorrecto
                textField.setStyle("-fx-prompt-text-fill: red");
                textField.setPromptText("Introduce una cantidad de horas entre 0 y 600");
                isOk = false;
            }

        } catch (NumberFormatException e) {
            textField.setText(""); // Limpiar el campo en caso de un valor incorrecto
            textField.setStyle("-fx-prompt-text-fill: red");
            textField.setPromptText("Introduce un numero válido");
            isOk = false;
        }
        return isOk;
    }

    @javafx.fxml.FXML
    public void verActividades(ActionEvent actionEvent) {
        App.loadFXML("viewsProfesor/view-actividades.fxml");
    }
}