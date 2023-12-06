package com.cesur.aplicaciondual.controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class EditAndShowAlumnoController implements Initializable {
    @javafx.fxml.FXML
    private Circle marcoImagen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image foto = new Image("img/alumno.jpg",false);
        marcoImagen.setFill(new ImagePattern(foto));
    }
}