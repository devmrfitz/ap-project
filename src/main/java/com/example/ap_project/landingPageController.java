package com.example.ap_project;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class landingPageController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void getMenuPage(ActionEvent event) throws IOException {
        System.out.println("you Clicked Menu button");
        FXMLLoader fxmlLoader = new FXMLLoader(game.class.getResource("menuPage.fxml"));
        Stage.getWindows().get(0).getScene();
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    void initialize() {
        System.out.println("hello");
    }
}
