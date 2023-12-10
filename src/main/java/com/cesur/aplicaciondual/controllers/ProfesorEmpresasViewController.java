package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.actividad.Actividad;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.w3c.dom.events.Event;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfesorEmpresasViewController implements Initializable {

    private final EmpresaDAOImp empresaDAOImp = new EmpresaDAOImp();

    private ObservableList<Empresa> listaEmpresas;

    private ObservableList<Empresa> listaEmpresasFiltro;

    private ObservableList<Empresa> getListaEmpresasTransicion;


    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cNombreEmpresa;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cTelefonoEmpresa;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cEmail;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cUbicacion;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cResponsable;
    @javafx.fxml.FXML
    private TableView tablaEmpresas;
    @javafx.fxml.FXML
    private Button btnEliminarEmpresa;
    @javafx.fxml.FXML
    private TextField txtNombreFiltroEmpresa;
    @javafx.fxml.FXML
    private Button añadirEmpresa;
    @javafx.fxml.FXML
    private Button btnVolver;


    @Deprecated
    public void logOut(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listaEmpresas = FXCollections.observableList(empresaDAOImp.getAll());

        listaEmpresasFiltro = FXCollections.observableArrayList();

        getListaEmpresasTransicion = FXCollections.observableArrayList();
        rellenarTabla();

        txtNombreFiltroEmpresa.textProperty().addListener((observable, s, t1) -> filtrar());


    }

    private void rellenarTabla() {

        tablaEmpresas.getItems().clear();

        ObservableList<Empresa> listaEmpresas = FXCollections.observableList(empresaDAOImp.getAll());


        cNombreEmpresa.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
        cResponsable.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getResponsable()));
        cTelefonoEmpresa.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getTelefono()));
        cEmail.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getEmail()));


        tablaEmpresas.getItems().addAll(listaEmpresas);

        //Al haces doble click cambia de pantalla
        tablaEmpresas.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tablaEmpresas.getSelectionModel().getSelectedItem() != null) {
                Empresa empresaSeleccionada = (Empresa) tablaEmpresas.getSelectionModel().getSelectedItem();
                Session.setEmpresa(empresaSeleccionada);
                App.loadFXML("viewsProfesor/enterprise-view-profesor.fxml");
            }
        });

    }


    private void filtrar() {

        listaEmpresasFiltro = FXCollections.observableArrayList();

        actualizarTabla();

        filtroNombre();

        actualizarTabla();

    }

    private void filtroNombre() {

        if (txtNombreFiltroEmpresa.getText() != null && !txtNombreFiltroEmpresa.getText().isEmpty()) {

            for (Empresa empresa : listaEmpresas) {

                if (empresa.getNombre().contains(txtNombreFiltroEmpresa.getText())) {

                    listaEmpresasFiltro.add(empresa);

                }
            }

            getListaEmpresasTransicion = FXCollections.observableList(new ArrayList<>(listaEmpresasFiltro));

        } else {

            listaEmpresasFiltro = FXCollections.observableList(new ArrayList<>(listaEmpresas));

            getListaEmpresasTransicion = FXCollections.observableList(new ArrayList<>(listaEmpresasFiltro));

        }
    }

    private void actualizarTabla() {

        tablaEmpresas.getItems().clear();

        tablaEmpresas.refresh();

        tablaEmpresas.getItems().addAll(listaEmpresasFiltro);

    }


    @javafx.fxml.FXML
    public void eliminarEmpresa(ActionEvent actionEvent) {
        Alert alert = App.makeNewAlert(Alert.AlertType.CONFIRMATION, "Eliminar Tarea", "¿Estas seguro de que quieres eliminar este alumno?", "Los cambios seran permanentes");

        alert.showAndWait().ifPresent((response) -> {

            if (response == ButtonType.OK && (tablaEmpresas.getSelectionModel().getSelectedItem() != null)) {

                empresaDAOImp.delete((Empresa) tablaEmpresas.getSelectionModel().getSelectedItem());

                Session.getProfesor().getAlumnos().remove(tablaEmpresas.getSelectionModel().getSelectedItem());

                // Session.getAlumno().getActividades().remove();

                tablaEmpresas.getItems().remove(tablaEmpresas.getSelectionModel().getSelectedItem());
                tablaEmpresas.refresh();


            }

        });

    }



    @javafx.fxml.FXML
    public void añadirEmpresa(ActionEvent actionEvent) {
        Empresa e = new Empresa();

        Session.setEmpresa(e);

        App.loadFXML("viewsProfesor/enterprise-view-profesor.fxml");
    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {

        App.loadFXML("viewsProfesor/main-view-profesor.fxml");

    }
}