package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.actividad.ActividadDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.text.SimpleDateFormat;

/**
 * Controlador para la visualización de actividades de un alumno.
 */
public class ViewActividadesController {
    @javafx.fxml.FXML
    private Circle circle;
    @javafx.fxml.FXML
    private Label labelAlumno;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private Label labelTotal;
    @javafx.fxml.FXML
    private TableView<Actividad> tablaActividades;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cNombreActividad;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cTipoPractica;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cHoras;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cObservaciones;

    /**
     * Inicializa el controlador y configura la visualización de actividades.
     */
    @javafx.fxml.FXML
    public void initialize() {
        // Configurar la información del alumno y su foto
        labelAlumno.setText(Session.getAlumno().getNombre() + " " + Session.getAlumno().getApellidos());
        Image foto = new Image("img/usuario.png", false);
        circle.setFill(new ImagePattern(foto));

        // Obtener las actividades del alumno
        var actividadDAO = new ActividadDAOImp();
        ObservableList<Actividad> actividades = FXCollections.observableList(actividadDAO.getAll(Session.getAlumno()));

        // Mostrar el total de actividades
        labelTotal.setText(labelTotal.getText() + actividades.size());

        // Configurar las columnas de la tabla
        cNombreActividad.setCellValueFactory((fila) -> {
            String nombre = fila.getValue().getActividad_realizada();
            return new SimpleStringProperty(nombre);
        });

        cTipoPractica.setCellValueFactory((fila) -> {
            String practica = fila.getValue().getTipo_practica();
            return new SimpleStringProperty(practica);
        });

        cFecha.setCellValueFactory((fila) -> {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            // Convertir la fecha a una cadena con el formato especificado
            String fechaComoString = formatoFecha.format(fila.getValue().getFecha());
            return new SimpleStringProperty(fechaComoString);
        });

        cHoras.setCellValueFactory((fila) -> {
            String horas = fila.getValue().getHoras_realizadas() + "";
            return new SimpleStringProperty(horas);
        });

        cObservaciones.setCellValueFactory((fila) -> {
            String observaciones = fila.getValue().getObservaciones();
            return new SimpleStringProperty(observaciones);
        });

        // Establecer las actividades en la tabla
        tablaActividades.setItems(actividades);
    }

    /**
     * Maneja el evento de volver a la pantalla de edición y visualización de un alumno.
     *
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void volverAEdit(ActionEvent actionEvent) {
        App.loadFXML("viewsProfesor/editAndShowAlumno.fxml",750,1100);
    }
}