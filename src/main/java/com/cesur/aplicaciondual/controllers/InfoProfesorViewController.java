package com.cesur.aplicaciondual.controllers;

import com.cesur.aplicaciondual.App;
import com.cesur.aplicaciondual.Session;
import com.cesur.aplicaciondual.domain.entities.profesor.ProfesorDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoProfesorViewController implements Initializable {

    private Image im;
    private  final ProfesorDAOImp profesorDAOImp = new ProfesorDAOImp();

    @javafx.fxml.FXML
    private Circle circulo;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextField txtApellido;
    @javafx.fxml.FXML
    private Button btnAceptar;
    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private PasswordField txtPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

         im = new Image("img/profes/JoseAntonio.jpeg",false);
        circulo.setFill(new ImagePattern(im));
        rellenarCampos();


    }

    private void rellenarCampos() {
        txtNombre.setText(Session.getProfesor().getNombre());
        txtApellido.setText(Session.getProfesor().getApellidos());
        txtPass.setText(Session.getProfesor().getPass());
        txtEmail.setText(Session.getProfesor().getEmail());
    }


    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {

        asignarDatos();

        profesorDAOImp.update(Session.getProfesor());

        App.loadFXML("fxml/login-view.fxml");

    }

    private void asignarDatos() {
        Session.getProfesor().setPass(txtPass.getText());
        Session.getProfesor().setEmail(txtEmail.getText());
        Session.getProfesor().setNombre(txtNombre.getText());
        Session.getProfesor().setApellidos(txtApellido.getText());
    }


    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {

        App.loadFXML("fxml/viewsProfesor/main-view-profesor.fxml");


    }
}

