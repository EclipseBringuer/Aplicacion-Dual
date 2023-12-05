package com.cesur.aplicaciondual;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import com.cesur.aplicaciondual.domain.entities.profesor.Profesor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

public class App extends Application {

    public static Stage myStage;
    @Override
    public void start(Stage stage) throws IOException {

        myStage = stage;

        System.out.println(HibernateUtil.getSessionFactory());
        /*
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Profesor> q = s.createQuery("from Profesor",Profesor.class);
            var profesores = q.getResultList();
            System.out.println(profesores);
        }*/
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/viewsAlumno/main-view-alumno.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void loadFXML(String fxml){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));

            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 1000, 1300);
            myStage.setScene(scene);

        } catch (IOException e) {
            System.out.println("Error al cargar el fxml");
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {
        launch();
    }
}