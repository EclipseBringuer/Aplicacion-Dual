package com.cesur.aplicaciondual;

import com.cesur.aplicaciondual.domain.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    public static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {

        myStage = stage;

        System.out.println(HibernateUtil.getSessionFactory());
        Image imagen = new Image(getClass().getResourceAsStream("/img/logos/Cesur.png"));
        /*
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Profesor> q = s.createQuery("from Profesor",Profesor.class);
            var profesores = q.getResultList();
            System.out.println(profesores);
        }*/
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.getIcons().add(imagen);
        stage.setResizable(false);
        stage.show();
    }


    public static void loadFXML(String fxml) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml));

            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            myStage.setScene(scene);

        } catch (IOException e) {
            System.out.println("Error al cargar el fxml");
            throw new RuntimeException(e);
        }

    }

    public static Alert makeNewAlert(Alert.AlertType type, String title, String header, String content) {
        Image imagen = new Image(App.class.getResourceAsStream("/img/logos/Cesur.png"));
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setGraphic(imageView);
        Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
        st.getIcons().add(imagen);
        return alert;
    }

    public static void main(String[] args) {
        launch();
    }
}
