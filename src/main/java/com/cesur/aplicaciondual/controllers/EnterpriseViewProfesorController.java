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

public class EnterpriseViewProfesorController implements Initializable {

    private Image img;
    private final EmpresaDAOImp empresaDAOImp = new EmpresaDAOImp();
    private final AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

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

        Session.setEmpresa(Session.getEmpresa());

        if (Session.getEmpresa().getId() == null) {

            nueva = true;

        }


        img = new Image("/img/NoImg.png");
        imgEmpresa.setImage(img);

        completarInformacion();


    }

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

        App.loadFXML("fxml/login-view.fxml",700,900);


    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {

        Session.setEmpresa(null);

        App.loadFXML("viewsProfesor/profesor-empresas-view.fxml",750,1100);



    }
}