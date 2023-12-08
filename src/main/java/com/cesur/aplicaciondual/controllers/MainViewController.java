package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @javafx.fxml.FXML
    private Button btnGear;
    @javafx.fxml.FXML
    private ContextMenu contextMenu;
    @javafx.fxml.FXML
    private MenuItem btnLogout;
    @javafx.fxml.FXML
    private TextField textFieldNombre;
    @javafx.fxml.FXML
    private ComboBox<Empresa> comboEmpresa;
    @javafx.fxml.FXML
    private RadioButton radioTodos;
    @javafx.fxml.FXML
    private RadioButton radioFct;
    @javafx.fxml.FXML
    private RadioButton radioDual;
    @javafx.fxml.FXML
    private Label labelNombre;
    @javafx.fxml.FXML
    private TableView<Alumno> tableAlumnos;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cApellidos;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cDual;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cFCT;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cEmpresa;
    @javafx.fxml.FXML
    private ToggleGroup tipo;
    @javafx.fxml.FXML
    private Circle circle;
    @javafx.fxml.FXML
    private ImageView imgAdd;
    private ObservableList<Alumno> listaAlumnosFiltro;
    private ObservableList<Alumno> listaAlumnos;
    private ObservableList<Alumno> listaAlumnosTransicion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image("img/alumnos/Samu.jpg", false);
        circle.setFill(new ImagePattern(img));
        labelNombre.setText(Session.getProfesor().getNombre());

        listaAlumnos = FXCollections.observableArrayList(Session.getProfesor().getAlumnos());
        listaAlumnosFiltro = FXCollections.observableArrayList();
        listaAlumnosTransicion = FXCollections.observableArrayList();

        completarTabla();

        textFieldNombre.textProperty().addListener(((observableValue, s, t1) -> filtrar()));
        tipo.selectedToggleProperty().addListener(((observableValue, s, t1) -> filtrar()));
        comboEmpresa.valueProperty().addListener(((observableValue, s, t1) -> filtrar()));
    }

    private void completarTabla() {
        cNombre.setCellValueFactory((fila -> new SimpleStringProperty(fila.getValue().getNombre())));
        cApellidos.setCellValueFactory((fila) -> new SimpleStringProperty(fila.getValue().getApellidos()));
        cFCT.setCellValueFactory((fila) -> {
            String fct = String.valueOf(fila.getValue().getFct());
            return new SimpleStringProperty(fct);
        });
        cDual.setCellValueFactory((fila) -> {
            String dual = String.valueOf(fila.getValue().getFct());
            return new SimpleStringProperty(dual);
        });
        cEmpresa.setCellValueFactory((fila) -> {
            String value = "Ninguna";
            if (fila.getValue().getEmpresa() != null) {
                value = fila.getValue().getEmpresa().getNombre();
            }
            return new SimpleStringProperty(value);
        });
        tableAlumnos.getItems().addAll(listaAlumnos);
    }

    private void actualizarTabla() {
        tableAlumnos.getItems().clear();
        tableAlumnos.refresh();
        tableAlumnos.getItems().addAll(listaAlumnosFiltro);
    }

    private void filtrar() {
        filtroRadio();
        actualizarTabla();
    }

    private void filtroRadio() {

        if (tipo.getSelectedToggle() == radioDual) {

            listaAlumnosFiltro.removeIf(alumno -> alumno.getDual() == 0);

        } else if (tipo.getSelectedToggle() == radioFct) {

            listaAlumnosFiltro.removeIf(alumno -> alumno.getFct() == 0);

        } else {

            listaAlumnosFiltro = FXCollections.observableArrayList(new ArrayList<>(listaAlumnosTransicion));

        }
    }

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
    public void logOut(ActionEvent actionEvent) {
        Session.setProfesor(null);
        App.loadFXML("login-view.fxml");
    }

    @javafx.fxml.FXML
    public void addAlumno(Event event) {
        App.loadFXML("viewsProfesor/editAndShowAlumno.fxml");
    }
}