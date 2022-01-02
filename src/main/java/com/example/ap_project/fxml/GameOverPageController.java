package com.example.ap_project.fxml;

import com.example.ap_project.exceptions.InsufficientCoinsException;
import com.example.ap_project.exceptions.NoSaveFoundException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameOverPageController {
    private transient Stage stage;

    @FXML
    private transient ResourceBundle resources;

    @FXML
    private transient URL location;

    @FXML
    private transient Pane pane;

    @FXML
    void respawn(ActionEvent event) throws NoSaveFoundException {

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("out_temp.o"))) {
            Object a = in.readObject();
            System.out.println("test" + a);
            System.out.println("play clicked");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("game.fxml")));
            GameController.setInstance((GameController) a);
            loader.setController(a);
            Scene scene = loader.load();
            //Move back a little to get a good view of the sphere
            stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
            GameController.setStage(stage);
//        ((GameController)(loader.getController())).setStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaxWidth(900);
            stage.setMaxHeight(480);
            stage.show();
            ((GameController)a).getHero().revive();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new NoSaveFoundException("No save file exists");
        } catch (InsufficientCoinsException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void getMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuPage.fxml")));
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        welcome();
    }

    public void welcome() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        final KeyFrame kf = new KeyFrame(Duration.millis(700), new KeyValue(pane.prefWidthProperty(), 501,
                Interpolator.EASE_BOTH));
        final KeyFrame kf2 = new KeyFrame(Duration.millis(700), new KeyValue(pane.prefHeightProperty(), 326,
                Interpolator.EASE_BOTH));

        final KeyFrame kf3 = new KeyFrame(Duration.millis(700), new KeyValue(pane.layoutXProperty(), 189,
                Interpolator.EASE_BOTH));
        final KeyFrame kf4 = new KeyFrame(Duration.millis(700), new KeyValue(pane.layoutYProperty(), 77,
                Interpolator.EASE_BOTH));



        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);
        timeline.getKeyFrames().add(kf3);
        timeline.getKeyFrames().add(kf4);
        timeline.play();
    }

    public void farewell() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        final KeyFrame kf = new KeyFrame(Duration.millis(700), new KeyValue(pane.prefWidthProperty(), 0,
                Interpolator.EASE_BOTH));
        final KeyFrame kf2 = new KeyFrame(Duration.millis(700), new KeyValue(pane.prefHeightProperty(), 0,
                Interpolator.EASE_BOTH));

        final KeyFrame kf3 = new KeyFrame(Duration.millis(700), new KeyValue(pane.layoutXProperty(), 450,
                Interpolator.EASE_BOTH));
        final KeyFrame kf4 = new KeyFrame(Duration.millis(700), new KeyValue(pane.layoutYProperty(), 240,
                Interpolator.EASE_BOTH));

        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf2);
        timeline.getKeyFrames().add(kf3);
        timeline.getKeyFrames().add(kf4);
        timeline.play();
    }
}
