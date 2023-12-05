package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import com.cesur.aplicaciondual.domain.entities.profesor.ProfesorDAOImp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewProfesorController implements Initializable {

    private final ProfesorDAOImp profesorDAOImp = new ProfesorDAOImp();
    private final EmpresaDAOImp empresaDAOImp = new EmpresaDAOImp();


    @javafx.fxml.FXML
    private Circle circle;
    @javafx.fxml.FXML
    private Button btnGear;
    @javafx.fxml.FXML
    private ContextMenu contextMenu;
    @javafx.fxml.FXML
    private TableColumn cAcciones;
    @javafx.fxml.FXML
    private Label lblNombreProfesor;
    @javafx.fxml.FXML
    private TableColumn <Alumno, String>cNombreAlumno;
    @javafx.fxml.FXML
    private TableColumn<Alumno, Integer> cHorasDual;
    @javafx.fxml.FXML
    private TableColumn <Alumno, Integer>cHorasFtc;
    @javafx.fxml.FXML
    private TableColumn<Alumno,String> cEmpresa;
    @javafx.fxml.FXML
    private ComboBox comboEmpresa;
    @javafx.fxml.FXML
    private ComboBox comboTipoPractica;
    @javafx.fxml.FXML
    private ComboBox comboNombreAlumno;
    @javafx.fxml.FXML
    private Button btnFiltrarAlumno;
    @javafx.fxml.FXML
    private TableView<Alumno>tablaAlumnos;


    /**
     * Inicializa el controlador después de que su raíz haya sido completamente procesada.
     *
     * @param url            La ubicación relativa del archivo FXML.
     * @param resourceBundle Los recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Establece el profesor en la sesión
        Session.setProfesor(profesorDAOImp.get(2));

        // Completa la información en la interfaz gráfica
        completarInformacion();

        rellenarTabla();
    }

    /**
     * Completa la información en la interfaz gráfica.
     */
    private void completarInformacion() {

        // Obtiene la lista de alumnos del profesor
        List<Alumno> alumnos = Session.getProfesor().getAlumnos();

        // Establece el nombre del profesor en el etiqueta
        if (Session.getProfesor().getNombre() != null) {
            lblNombreProfesor.setText(Session.getProfesor().getNombre());
        } else {
            lblNombreProfesor.setText("Nombre del profesor");
        }



        // Llena el ComboBox de nombres de alumnos
        List<String> nombresAlumnos = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            String nombreCompleto = alumno.getNombre() + " " + alumno.getApellidos();
            nombresAlumnos.add(nombreCompleto);
        }
        ObservableList<String> nombres = FXCollections.observableArrayList(nombresAlumnos);
        comboNombreAlumno.setItems(nombres);



        // Recorre la lista de alumnos y obtiene los nombres de las empresas
        List<String> nombresEmpresas = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            Empresa empresa = alumno.getEmpresa();
            if (empresa != null) {
                nombresEmpresas.add(empresa.getNombre());
            }
        }

        // Llena el ComboBox de nombres de empresas
        ObservableList<String> itemsEmpresas = FXCollections.observableArrayList(nombresEmpresas);
        comboEmpresa.setItems(itemsEmpresas);



    }

    private void rellenarTabla(){
        ObservableList<Alumno> listaAlumnos = FXCollections.observableList(Session.getProfesor().getAlumnos());

        cNombreAlumno.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
        cHorasDual.setCellValueFactory(fila -> new SimpleIntegerProperty(fila.getValue().getDual()).asObject());
        cHorasFtc.setCellValueFactory(fila -> new SimpleIntegerProperty(fila.getValue().getFct()).asObject());
        cEmpresa.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getEmpresa().getNombre()));

        tablaAlumnos.getItems().addAll(listaAlumnos);

    }


    /**
     * Maneja el evento de activación del botón Gear.
     *
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void botonGearActivate(ActionEvent actionEvent) {
        // Lógica para manejar la activación del botón Gear
    }

    @javafx.fxml.FXML
    public void filtrarAlumno(ActionEvent actionEvent) {



    }
}
