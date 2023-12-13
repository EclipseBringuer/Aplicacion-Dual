package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.alumno.AlumnoDAOImp;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de los datos una empresa
 */
public class EnterpriseViewProfesorController implements Initializable {

    private Image img;
    private final EmpresaDAOImp empresaDAOImp = new EmpresaDAOImp();

    private boolean nueva;


    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtTelefono;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextField txtResponsable;
    @javafx.fxml.FXML
    private TextArea txtObservaciones;
    @javafx.fxml.FXML
    private Button btnGuardar;
    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private ImageView imgEmpresa;
    @javafx.fxml.FXML
    private Label lblEmpresa;
    @javafx.fxml.FXML
    private Label lblUbicacion;
    @javafx.fxml.FXML
    private TextField txtImagen;
    @javafx.fxml.FXML
    private TextField txtUbicacion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(Session.getProfesor()==null) {

            Session.setEmpresa(Session.getAlumno().getEmpresa());

            prepararVistaParaAlumno();

        }


        if (Session.getEmpresa().getId() == null) {

            nueva = true;

        }


        img = new Image("/img/NoImg.png");
        imgEmpresa.setImage(img);

        completarInformacion();


    }

    /**
     * Metodo que prepara la vista si el que entra es un alumno
     */
    private void prepararVistaParaAlumno() {
        txtNombre.setEditable(false);
        txtEmail.setEditable(false);
        txtTelefono.setEditable(false);
        txtResponsable.setEditable(false);
        txtObservaciones.setEditable(false);
        txtUbicacion.setEditable(false);
        txtImagen.setEditable(false);
        btnGuardar.setVisible(false);
        btnCancelar.setText("Volver");
    }

    /**
     * Metodo que rellena la informacion de los campos al entrar
     */
    private void completarInformacion() {

        if (Session.getEmpresa().getNombre() != null) {
            lblEmpresa.setText(Session.getEmpresa().getNombre());
        } else lblEmpresa.setText("Nombre de la empresa");

        lblEmpresa.setText(Session.getEmpresa().getNombre());
        txtNombre.setText(Session.getEmpresa().getNombre());
        txtEmail.setText(Session.getEmpresa().getEmail());
        txtTelefono.setText(Session.getEmpresa().getTelefono());
        txtResponsable.setText(Session.getEmpresa().getResponsable());
        txtObservaciones.setText(Session.getEmpresa().getObservaciones());
        txtUbicacion.setText(Session.getEmpresa().getUbicacion());
        txtImagen.setText(Session.getEmpresa().getLogo());

        if (Session.getEmpresa().getLogo() != null) {

            img = new Image(Session.getEmpresa().getLogo());
            imgEmpresa.setImage(img);

        }

        if (Session.getEmpresa().getUbicacion() != null) {
            lblUbicacion.setText(Session.getEmpresa().getUbicacion());
        } else lblUbicacion.setText("Ubicación");

    }

    /**
     * Metodo que asigna los cambios a la empresa
     */
    private void asignarCampos() {

        Session.getEmpresa().setTelefono(txtTelefono.getText());
        Session.getEmpresa().setNombre(txtNombre.getText());
        Session.getEmpresa().setObservaciones(txtObservaciones.getText());
        Session.getEmpresa().setResponsable(txtResponsable.getText());
        Session.getEmpresa().setEmail(txtEmail.getText());
        Session.getEmpresa().setLogo(txtImagen.getText());
        Session.getEmpresa().setUbicacion(txtUbicacion.getText());

    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {

        asignarCampos();

        if (!nueva) {

            empresaDAOImp.update(Session.getEmpresa());

        } else {

            empresaDAOImp.save(Session.getEmpresa());

        }


        Session.setEmpresa(null);

        App.loadFXML("viewsProfesor/profesor-empresas-view.fxml", 1000, 1400);


    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {

        if(Session.getProfesor()==null) {

            Session.setEmpresa(null);

            App.loadFXML("viewsAlumno/main-view-alumno.fxml", 1000, 1400);


        }else{
            Session.setEmpresa(null);

            App.loadFXML("viewsProfesor/profesor-empresas-view.fxml", 1000, 1400);

        }
        
    }
}