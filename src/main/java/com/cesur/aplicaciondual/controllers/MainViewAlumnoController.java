package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import java.util.*;

/**
 * Controlador main-view del Alumno
 *
 * @author samu_
 */
public class MainViewAlumnoController implements Initializable {

    private final AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

    private ObservableList<Actividad> listaActividades;

    private ObservableList<Actividad> listaActividadesFiltro;
    private ObservableList<Actividad> listaActividadesTransicion;

    @javafx.fxml.FXML
    private TextField txtFiltroNombreTarea;
    @javafx.fxml.FXML
    private RadioButton radioDual;
    @javafx.fxml.FXML
    private RadioButton radioFCT;
    @javafx.fxml.FXML
    private RadioButton radioTodas;
    @javafx.fxml.FXML
    private DatePicker filtroFecha;
    @javafx.fxml.FXML
    private Label lblNombreTutor;
    @javafx.fxml.FXML
    private TableView<Actividad> tablaActividades;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cNombreActividad;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cTipoPractica;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Actividad, Integer> cHoras;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cObservaciones;
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
     *
     * @param url            **
     * @param resourceBundle **
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Session.setAlumno(alumnoDAOImp.get("23456789Y"));

        listaActividades = FXCollections.observableList(Session.getAlumno().getActividades());

        listaActividadesFiltro = FXCollections.observableList(Session.getAlumno().getActividades());

        listaActividadesTransicion=FXCollections.observableList(Session.getAlumno().getActividades());


        //--------Vista general---------
        if (Session.getAlumno() != null) {

            completarInformacion();

            completarTabla();
        }


        //--------Listener a la propiedad del filtro tareas---------
        txtFiltroNombreTarea.textProperty().addListener((observable, s, t1) -> {

            listaActividadesFiltro = FXCollections.observableArrayList();

            if (txtFiltroNombreTarea.getText() != null && !txtFiltroNombreTarea.getText().isEmpty()) {

                for (Actividad actividad : listaActividades) {

                    if (actividad.getActividad_realizada().contains(txtFiltroNombreTarea.getText())) {

                        listaActividadesFiltro.add(actividad);

                    }

                }

                Set<Actividad> setActividadesFiltro = new HashSet<>(listaActividadesFiltro);

                listaActividadesFiltro = FXCollections.observableList(new ArrayList<>(setActividadesFiltro));


                tablaActividades.getItems().clear();

                tablaActividades.refresh();

                tablaActividades.getItems().addAll(listaActividadesFiltro);

                listaActividadesTransicion=FXCollections.observableList(new ArrayList<>(listaActividadesFiltro));

            }

        });


        //------Radios-----


        tipo.selectedToggleProperty().addListener((observable, s, t1) -> {

            if (t1 == radioDual) {

                listaActividadesFiltro=FXCollections.observableList(new ArrayList<>(listaActividadesTransicion));

                listaActividadesFiltro.removeIf(actividad -> actividad.getTipo_practica().equalsIgnoreCase("fct"));

                tablaActividades.getItems().clear();

                tablaActividades.refresh();

                tablaActividades.getItems().addAll(listaActividadesFiltro);

            } else if (t1 == radioFCT) {

                listaActividadesFiltro=FXCollections.observableList(new ArrayList<>(listaActividadesTransicion));

                listaActividadesFiltro.removeIf(actividad -> actividad.getTipo_practica().equalsIgnoreCase("dual"));

                tablaActividades.getItems().clear();

                tablaActividades.refresh();

                tablaActividades.getItems().addAll(listaActividadesFiltro);


            } else {

                listaActividadesFiltro=FXCollections.observableList(new ArrayList<>(listaActividadesTransicion));

                tablaActividades.getItems().clear();

                tablaActividades.refresh();

                tablaActividades.getItems().addAll(listaActividadesFiltro);

            }

        });
    }

    private void completarTabla() {

        cNombreActividad.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getActividad_realizada())));

        cTipoPractica.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getTipo_practica())));

        cFecha.setCellValueFactory((fila -> new SimpleStringProperty((new SimpleDateFormat("dd-MM-yyyy").format(fila.getValue().getFecha())))));

        cHoras.setCellValueFactory((fila -> new SimpleObjectProperty<>(fila.getValue().getHoras_realizadas())));

        cObservaciones.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getObservaciones())));

        tablaActividades.getItems().addAll(listaActividades);

    }

    private void completarInformacion() {

        lblNombreAlumno.setText(Session.getAlumno().getNombre() + " " + Session.getAlumno().getApellidos());

        labelHorasDual.setText(Session.getAlumno().getDual() + "/600 horas Dual Completadas");

        labelHorasFCT.setText(Session.getAlumno().getFct() + "/600 horas Fct Completadas");

        lblNombreTutor.setText(Session.getAlumno().getProfesor().getNombre());

        progresBarDual.setProgress((double) Session.getAlumno().getDual() / 600);

        progresBarFCT.setProgress((double) Session.getAlumno().getFct() / 600);

        Image img = new Image("img/alumnos/Samu.jpg", false);

        circle.setFill(new ImagePattern(img));

    }


    /**
     * Listener del boton gear
     *
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