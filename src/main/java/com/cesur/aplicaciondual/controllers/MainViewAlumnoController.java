package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.actividad.ActividadDAOImp;
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
import org.hibernate.Transaction;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.MouseEvent;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Controlador main-view del Alumno
 *
 * @author samu_
 */
public class MainViewAlumnoController implements Initializable {

    private final AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

    private final ActividadDAOImp actividadDAOImp = new ActividadDAOImp();

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
    @javafx.fxml.FXML
    private MenuItem btnLogOut;
    @javafx.fxml.FXML
    private Button btnAñadir;
    @javafx.fxml.FXML
    private Button btnEliminar;


    /**
     * Inicializador de la main view
     *
     * @param url            **
     * @param resourceBundle **
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Session.getAlumno().setActividades(actividadDAOImp.getAll(Session.getAlumno()));

        listaActividades = FXCollections.observableList(Session.getAlumno().getActividades());

        listaActividadesFiltro = FXCollections.observableArrayList();

        listaActividadesTransicion = FXCollections.observableArrayList();


        //--------Vista general---------
        if (Session.getAlumno() != null) {

            completarInformacion();

            completarTabla();

        }


        txtFiltroNombreTarea.textProperty().addListener((observable, s, t1) -> filtrar());

        tipo.selectedToggleProperty().addListener((observable, s, t1) -> filtrar());

        filtroFecha.valueProperty().addListener((observable, s, t1) -> filtrar());

    }


    private void filtrar() {

        listaActividadesFiltro = FXCollections.observableArrayList();

        actualizarTabla();

        filtroNombre();

        filtroRadio();

        filtroFecha();

        actualizarTabla();

    }

    private void filtroNombre() {

        if (txtFiltroNombreTarea.getText() != null && !txtFiltroNombreTarea.getText().isEmpty()) {

            for (Actividad actividad : listaActividades) {

                if (actividad.getActividad_realizada().contains(txtFiltroNombreTarea.getText())) {

                    listaActividadesFiltro.add(actividad);

                }
            }

            listaActividadesTransicion = FXCollections.observableList(new ArrayList<>(listaActividadesFiltro));

        } else {

            listaActividadesFiltro = FXCollections.observableList(new ArrayList<>(listaActividades));

            listaActividadesTransicion = FXCollections.observableList(new ArrayList<>(listaActividadesFiltro));

        }
    }

    private void actualizarTabla() {

        tablaActividades.getItems().clear();

        tablaActividades.refresh();

        tablaActividades.getItems().addAll(listaActividadesFiltro);

    }



    private void filtroRadio() {

        if (tipo.getSelectedToggle() == radioDual) {

            listaActividadesFiltro.removeIf(actividad -> actividad.getTipo_practica().equalsIgnoreCase("fct"));

        } else if (tipo.getSelectedToggle() == radioFCT) {

            listaActividadesFiltro.removeIf(actividad -> actividad.getTipo_practica().equalsIgnoreCase("dual"));

        } else {

            listaActividadesFiltro = FXCollections.observableArrayList(new ArrayList<>(listaActividadesTransicion));

        }
    }

    private void filtroFecha() {

        if (filtroFecha.getValue() != null) {
            listaActividadesFiltro.removeIf(actividad -> {

                LocalDate seleccionada = filtroFecha.getValue();

                // Convertir Date a LocalDate
                Instant instant = actividad.getFecha().toInstant();
                LocalDate fechaActividad = instant.atZone(ZoneId.systemDefault()).toLocalDate();


                // Comparar LocalDate de la actividad con la fecha seleccionada
                return !fechaActividad.equals(seleccionada);

            });
        }


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
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {

        Session.setAlumno(null);

        App.loadFXML("login-view.fxml",700,900);

    }

    @javafx.fxml.FXML
    public void AñadirActividad(ActionEvent actionEvent) {

        Actividad act = new Actividad();

        act.setAlumno(Session.getAlumno());

        Session.setActividad(act);

        App.loadFXML("viewsAlumno/add-actividad-view.fxml",750,1100);

    }

    @javafx.fxml.FXML
    public void eliminarActividad(ActionEvent actionEvent) {

    Alert alert = App.makeNewAlert(Alert.AlertType.CONFIRMATION,"Eliminar Tarea","¿Estas seguro de que quieres eliminar esta actividad?","Los cambios seran permanentes");

        alert.showAndWait().ifPresent(response -> {

            if (response == ButtonType.OK && (tablaActividades.getSelectionModel().getSelectedItem() != null)) {

                    actividadDAOImp.delete(tablaActividades.getSelectionModel().getSelectedItem());

                    Session.getAlumno().getActividades().remove(tablaActividades.getSelectionModel().getSelectedItem());

                    tablaActividades.getItems().remove(tablaActividades.getSelectionModel().getSelectedItem());
                    tablaActividades.refresh();

            }

        });

    }
}