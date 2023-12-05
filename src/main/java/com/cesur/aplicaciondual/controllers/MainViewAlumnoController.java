package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.w3c.dom.events.Event;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controlador main-view del Alumno
 * @author samu_
 */
public class MainViewAlumnoController implements Initializable {

    private final AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

    @javafx.fxml.FXML
    private TextField txtFiltroNombreTarea;
    @javafx.fxml.FXML
    private RadioButton radioDual;
    @javafx.fxml.FXML
    private RadioButton RadioFCT;
    @javafx.fxml.FXML
    private DatePicker filtroFecha;
    @javafx.fxml.FXML
    private Label lblNombreTutor;
    @javafx.fxml.FXML
    private TableView<Actividad> tablaActividades;
    @javafx.fxml.FXML
    private TableColumn<Actividad,String> cNombreActividad;
    @javafx.fxml.FXML
    private TableColumn<Actividad,String> cTipoPractica;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Actividad,Integer> cHoras;
    @javafx.fxml.FXML
    private TableColumn<Actividad,String> cObservaciones;
    @javafx.fxml.FXML
    private TableColumn cAcciones;
    @javafx.fxml.FXML
    private Label labelHorasDual;
    @javafx.fxml.FXML
    private Label labelHorasFCT;
    @javafx.fxml.FXML
    private Label lblNombreAlumno;
    @javafx.fxml.FXML
    private ToggleGroup tipo;
    @javafx.fxml.FXML
    private Button btnGear;
    @javafx.fxml.FXML
    private ContextMenu contextMenu;
    @javafx.fxml.FXML
    private Circle circle;
    @javafx.fxml.FXML
    private ProgressBar progresBarDual;
    @javafx.fxml.FXML
    private ImageView imgRueda;
    @javafx.fxml.FXML
    private ProgressBar progresBarFCT;

    /**
     * Inicializador de la main view
     * @param url **
     * @param resourceBundle **
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Session.setAlumno(alumnoDAOImp.get("23456789Y"));

        ObservableList<Actividad> listaActividades = FXCollections.observableList(Session.getAlumno().getActividades());

        lblNombreAlumno.setText(Session.getAlumno().getNombre()+" "+Session.getAlumno().getApellidos());
        labelHorasDual.setText(Session.getAlumno().getDual()+"/600 horas Dual Completadas");
        labelHorasFCT.setText(Session.getAlumno().getFct()+"/600 horas Fct Completadas");
        lblNombreTutor.setText(Session.getAlumno().getProfesor().getNombre());
        progresBarDual.setProgress((double) Session.getAlumno().getDual() /600);
        progresBarFCT.setProgress((double) Session.getAlumno().getFct() /600);




        cNombreActividad.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getActividad_realizada())));
        cTipoPractica.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getTipo_practica())));
        cFecha.setCellValueFactory((fila -> new SimpleStringProperty((new SimpleDateFormat("dd-MM-yyyy").format(fila.getValue().getFecha())))));
        cHoras.setCellValueFactory((fila -> new SimpleObjectProperty<>(fila.getValue().getHoras_realizadas())));
        cObservaciones.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getObservaciones())));

        tablaActividades.getItems().addAll(listaActividades);

        Image img = new Image("img/alumnos/Samu.jpg",false);
        circle.setFill(new ImagePattern(img));




    }


    /**
     * Listener del boton gear
     * @param actionEvent accion del boton
     */
    @javafx.fxml.FXML
    public void botonGearActivate(ActionEvent actionEvent) {
        btnGear.setContextMenu(contextMenu);
        btnGear.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(btnGear, event.getScreenX(), event.getScreenY());
            }
        });
    }
}