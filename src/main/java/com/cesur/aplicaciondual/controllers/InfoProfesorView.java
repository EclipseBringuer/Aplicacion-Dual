package com.cesur.aplicaciondual.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoProfesorView implements Initializable {

    @javafx.fxml.FXML
    private Circle circulo;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextField txtApellido;
    @javafx.fxml.FXML
    private TextField txtContraseña;
    @javafx.fxml.FXML
    private Button btnAceptar;
    @javafx.fxml.FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image im = new Image("img/Profe.jpeg",false);
        circulo.setFill(new ImagePattern(im));


    }


    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
    }
}

