package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.w3c.dom.events.Event;

import java.net.URL;
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
    private TableView tablaActividades;
    @javafx.fxml.FXML
    private TableColumn cNombreActividad;
    @javafx.fxml.FXML
    private TableColumn cTipoPractica;
    @javafx.fxml.FXML
    private TableColumn cFecha;
    @javafx.fxml.FXML
    private TableColumn cHoras;
    @javafx.fxml.FXML
    private TableColumn cObservaciones;
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

    /**
     * Inicializador de la main view
     * @param url **
     * @param resourceBundle **
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Session.setAlumno(alumnoDAOImp.get("12345678A"));

        lblNombreAlumno.setText(Session.getAlumno().getNombre()+" "+Session.getAlumno().getApellidos());
        labelHorasDual.setText(Session.getAlumno().getDual()+"/600 horas Dual Completadas");
        labelHorasFCT.setText(Session.getAlumno().getFct()+"/600 horas Fct Completadas");
        lblNombreTutor.setText(Session.getAlumno().getProfesor().getNombre());


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