package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.ActividadDAOImp;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;


public class AddActividadViewController
{

    ActividadDAOImp actividadDAOImp = new ActividadDAOImp();

    @javafx.fxml.FXML
    private TextField txtHoras;
    @javafx.fxml.FXML
    private DatePicker datePickerFecha;
    @javafx.fxml.FXML
    private ComboBox<String> comboTipo;
    @javafx.fxml.FXML
    private TextArea txtActividadRealizada;
    @javafx.fxml.FXML
    private TextArea txtObservaciones;

    @javafx.fxml.FXML
    private Label lblActividad;

    @javafx.fxml.FXML
    private Button btnGuardar;

    @javafx.fxml.FXML
    private Button btnCancelar;

    @javafx.fxml.FXML
    public void initialize() {

        if(Session.getActividad().getId_actividad()!=null){
            lblActividad.setText("Editar Actividad");
        }

        completarCampos();


    }

    private void completarCampos() {
        if (Session.getActividad().getHoras_realizadas() !=null) {
            txtHoras.setText(Session.getActividad().getHoras_realizadas().toString());
        }
        datePickerFecha.setValue(LocalDate.now());
        comboTipo.getItems().add("FCT");
        comboTipo.getItems().add("Dual");
        comboTipo.getSelectionModel().selectFirst();
        txtActividadRealizada.setText(Session.getActividad().getActividad_realizada());
        txtObservaciones.setText(Session.getActividad().getActividad_realizada());
    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {

        volcarDatos();

        Session.getAlumno().getActividades().add(Session.getActividad());

        if(Session.getActividad().getId_actividad()==null){

            actividadDAOImp.save(Session.getActividad());
            Session.setActividad(null);
            App.loadFXML("viewsAlumno/main-view-alumno.fxml",750,1100);


        }else{

            actividadDAOImp.update(Session.getActividad());
            Session.setActividad(null);
            App.loadFXML("viewsAlumno/main-view-alumno.fxml",750,1100);

        }


    }

    private void volcarDatos() {
        Session.getActividad().setActividad_realizada(txtActividadRealizada.getText());

        Session.getActividad().setFecha(java.sql.Date.valueOf(datePickerFecha.getValue()));

        Session.getActividad().setObservaciones(txtObservaciones.getText());

        Session.getActividad().setTipo_practica(comboTipo.getValue());

        Session.getActividad().setHoras_realizadas(Integer.parseInt(txtHoras.getText()));
    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {

        Session.setActividad(null);

        App.loadFXML("viewsAlumno/main-view-alumno.fxml",750,1100);

    }
}