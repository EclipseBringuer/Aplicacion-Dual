package com.cesur.aplicaciondual.controllers;
import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.alumno.Alumno;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import com.cesur.aplicaciondual.domain.entities.profesor.ProfesorDAOImp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.events.Event;

import javax.swing.text.html.ImageView;
import java.io.IOException;
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
    private Label lblNombreProfesor;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cNombreAlumno;
    @javafx.fxml.FXML
    private TableColumn<Alumno, Integer> cHorasDual;
    @javafx.fxml.FXML
    private TableColumn<Alumno, Integer> cHorasFtc;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cEmpresa;
    @javafx.fxml.FXML
    private ComboBox comboEmpresa;
    @javafx.fxml.FXML
    private ComboBox comboTipoPractica;
    @javafx.fxml.FXML
    private ComboBox comboNombreAlumno;
    @javafx.fxml.FXML
    private Button btnFiltrarAlumno;
    @javafx.fxml.FXML
    private TableView<Alumno> tablaAlumnos;
    @javafx.fxml.FXML
    private MenuItem btnLogout;
    @FXML
    private TableColumn<Alumno, String> cApellidos;


    /**
     * Inicializa el controlador después de que su raíz haya sido completamente procesada.
     *
     * @param url            La ubicación relativa del archivo FXML.
     * @param resourceBundle Los recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Establece el profesor en la sesión

        Session.getProfesor();

        // Completa la información en la interfaz gráfica
        completarInformacion();

        rellenarTabla();

    }

    /**
     * Completa la información en la interfaz gráfica.
     */
    private void completarInformacion() {

        comboNombreAlumno.setPromptText("Selecciona Alumno");
        comboEmpresa.setPromptText("Selecciona Empresa");


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
        nombres.add(0, "Cualquiera");
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
        itemsEmpresas.add(0, "Cualquiera");
        comboEmpresa.setItems(itemsEmpresas);


        ObservableList<String> itemsTipoPractica = comboTipoPractica.getItems();

        // Agrega las opciones "Dual" y "FTC" si no están presentes
        if (!itemsTipoPractica.contains("Dual")) {
            itemsTipoPractica.add("Dual");
        }

        if (!itemsTipoPractica.contains("FTC")) {
            itemsTipoPractica.add("FTC");
        }

        // Puedes establecer un valor predeterminado si lo deseas
        comboTipoPractica.setValue("Dual");


        //Rellena la imagen que coincida con el profesor
        //Image img = new Image(Session.getProfesor().getImagen(),false);
        //circle.setFill(new ImagePattern(img));

    }

    private void rellenarTabla() {
        ObservableList<Alumno> listaAlumnos = FXCollections.observableList(Session.getProfesor().getAlumnos());


        cNombreAlumno.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
        cApellidos.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getApellidos()));
        cHorasDual.setCellValueFactory(fila -> new SimpleIntegerProperty(fila.getValue().getDual()).asObject());
        cHorasFtc.setCellValueFactory(fila -> new SimpleIntegerProperty(fila.getValue().getFct()).asObject());

        cEmpresa.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getEmpresa().getNombre()));


        tablaAlumnos.getItems().addAll(listaAlumnos);


        //Al haces doble click cambia de pantalla
        tablaAlumnos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tablaAlumnos.getSelectionModel().getSelectedItem() != null) {
                Alumno alumnoSeleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
                Session.setAlumno(alumnoSeleccionado);
                App.loadFXML("viewsProfesor/editAndShowAlumno.fxml");
            }
        });

    }


    /**
     * Maneja el evento de activación del botón Gear.
     *
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void botonGearActivate(ActionEvent actionEvent) {
        // Lógica para manejar la activación del botón Gear

        btnGear.setContextMenu(contextMenu);

        btnGear.setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {

                contextMenu.show(btnGear, event.getScreenX(), event.getScreenY());

            }

        });
    }


    /**
     * Maneja el evento de filtrar alumnos
     *
     * @param actionEvent
     */
    @FXML
    public void filtrarAlumno(ActionEvent actionEvent) {

        // Obtén la lista de alumnos original
        List<Alumno> alumnos = Session.getProfesor().getAlumnos();

        // Obtén el valor seleccionado en el ComboBox comboNombreAlumno
        String nombreAlumnoSeleccionado = (String) comboNombreAlumno.getValue();

        // Obtén el valor seleccionado en el ComboBox comboEmpresa
        String empresaSeleccionada = (String) comboEmpresa.getValue();

        // Obtén el valor seleccionado en el ComboBox comboTipoPractica
        String tipoPracticaSeleccionada = (String) comboTipoPractica.getValue();

        // Lista para almacenar los alumnos filtrados
        List<Alumno> alumnosFiltrados = new ArrayList<>();

        // Filtra la lista de alumnos basándote en el valor del ComboBox comboNombreAlumno, comboEmpresa y comboTipoPractica
        for (Alumno alumno : alumnos) {
            // Separa nombre y apellidos para hacer la comprobación
            String nombreCompletoAlumno = alumno.getNombre() + " " + alumno.getApellidos();

            // Verifica si ambos combos están seleccionados y coinciden
            if (("cualquiera".equalsIgnoreCase(nombreAlumnoSeleccionado) || nombreCompletoAlumno.equalsIgnoreCase(nombreAlumnoSeleccionado)) &&
                    ("cualquiera".equalsIgnoreCase(empresaSeleccionada) || alumno.getEmpresa().getNombre().equalsIgnoreCase(empresaSeleccionada)) &&
                    ("cualquiera".equalsIgnoreCase(tipoPracticaSeleccionada) || cumpleFiltroTipoPractica(alumno, tipoPracticaSeleccionada))) {
                alumnosFiltrados.add(alumno);
            }
        }


        // Limpia la tabla y agrega los alumnos filtrados
        tablaAlumnos.getItems().clear();
        tablaAlumnos.getItems().addAll(alumnosFiltrados);


        // Restaura los valores por defecto de los ComboBox
        comboNombreAlumno.getSelectionModel().selectFirst();
        comboEmpresa.getSelectionModel().selectFirst();

    }

    //blaaaa

    private boolean cumpleFiltroTipoPractica(Alumno alumno, String tipoPracticaSeleccionada) {
        // Verifica si el tipo de práctica seleccionado es "DUAL" y las horas dual son mayores que 0
        if ("DUAL".equalsIgnoreCase(tipoPracticaSeleccionada)) {
            return alumno.getDual() != null && alumno.getDual() > 0;
        }
        // Verifica si el tipo de práctica seleccionado es "FTC" y las horas FTC son mayores que 0
        else if ("FTC".equalsIgnoreCase(tipoPracticaSeleccionada)) {
            return alumno.getFct() != null && alumno.getFct() > 0;
        }
        // Otros casos (por si acaso)
        return false;

    }

        @javafx.fxml.FXML
        public void logOut (ActionEvent actionEvent){

            Session.setProfesor(null);

            App.loadFXML("login-view.fxml");

        }


    public void añadirAlumno(MouseEvent mouseEvent) {

        App.loadFXML("viewsProfesor/editAndShowAlumno.fxml");
    }
}
