package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.empresa.Empresa;
import com.cesur.aplicaciondual.domain.entities.empresa.EmpresaDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EnterpriseViewProfesor implements Initializable {

    Image img;
    EmpresaDAOImp empresaDAOImp = new EmpresaDAOImp();




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
    private ListView listAlumnos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        img = new Image("/img/NoImg.png");
        imgEmpresa.setImage(img);

        completarInformacion();


        //TODO rellenar el list view con los alumnos y interacciones

    }

    private void completarInformacion() {

        if(Session.getEmpresa().getNombre() != null) {
            lblEmpresa.setText(Session.getEmpresa().getNombre());
        }else lblEmpresa.setText("Nombre de la empresa");

        lblEmpresa.setText(Session.getEmpresa().getNombre());
        txtNombre.setText(Session.getEmpresa().getNombre());
        txtEmail.setText(Session.getEmpresa().getEmail());
        txtTelefono.setText(Session.getEmpresa().getTelefono());
        txtResponsable.setText(Session.getEmpresa().getResponsable());
        txtObservaciones.setText(Session.getEmpresa().getObservaciones());

        img = new Image(Session.getEmpresa().getLogo());
        imgEmpresa.setImage(img);

        if(Session.getEmpresa().getUbicacion() != null) {
            lblUbicacion.setText(Session.getEmpresa().getUbicacion());
        }else lblUbicacion.setText("Ubicación");

    }

    private void asignarCampos(){

        Session.getEmpresa().setTelefono(txtTelefono.getText());
        Session.getEmpresa().setNombre(txtNombre.getText());
        Session.getEmpresa().setObservaciones(txtObservaciones.getText());
        Session.getEmpresa().setResponsable(txtResponsable.getText());
        Session.getEmpresa().setEmail(txtEmail.getText());

    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {

        asignarCampos();

        empresaDAOImp.update(Session.getEmpresa());

        Session.setEmpresa(null);

        App.loadFXML("fxml/login-view.fxml");

    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        App.loadFXML("fxml/login-view.fxml");
    }
}