package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
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
import javafx.scene.shape.Circle;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfesorEmpresasViewController implements Initializable {

    private final EmpresaDAOImp empresaDAOImp = new EmpresaDAOImp();


    @javafx.fxml.FXML
    private Circle circle;
    @javafx.fxml.FXML
    private Button btnGear;
    @javafx.fxml.FXML
    private ContextMenu contextMenu;
    @javafx.fxml.FXML
    private MenuItem btnLogout;
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
    private Button btnFiltrarEmpresa;
    @javafx.fxml.FXML
    private ComboBox comboNombreEmpresa;
    @javafx.fxml.FXML
    private TableView tablaEmpresas;
    @javafx.fxml.FXML
    private Button btnEliminarEmpresa;


    @javafx.fxml.FXML
    public void botonGearActivate(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void eliminarAlumno(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rellenarTabla();


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



    @javafx.fxml.FXML
    public void filtrarEmpresa(ActionEvent actionEvent) {
    }
}