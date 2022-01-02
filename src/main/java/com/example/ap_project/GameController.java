package com.example.ap_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.ap_project.animation_timers.HeroInteractChecker;
import com.example.ap_project.animation_timers.JumpableFallChecker;
import com.example.ap_project.animation_timers.OrcJumpGravityHandler;
import com.example.ap_project.animation_timers.ScreenScroller;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
    private static Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane hero;

    @FXML
    private Rectangle platform1;

    @FXML
    private Rectangle platform2;

    @FXML
    private ImageView orc;

    @FXML
    private ImageView boss;

    @FXML
    private Region island1, island2, island3, island4;

    @FXML
    private ImageView sword;

    @FXML
    private Rectangle deathZone;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane cloudAnchorPane;

    private ArrayList<Interactable> interactables;

    private ArrayList<Island> islands;

    private ArrayList<FallingPlatform> fallingPlatforms;

    private ArrayList<Orc> orcs;

    private Hero hero_obj;

    @FXML
    void move(KeyEvent event) {
        System.out.println("moved");
        System.out.println(event.getSource());
    }

    @FXML
    void openChest(MouseEvent event) throws FileNotFoundException {
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();

        ImageView imageView = (ImageView) event.getSource();
        Chest coins = new CoinChest(5, imageView);
        coins.open();
        Weapon temp_weapon = new Sword(1);
        Chest weapon = new WeaponChest(temp_weapon,imageView);
        weapon.open();
    }

    @FXML
    void pause(MouseEvent event) throws IOException {
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();

        System.out.println("Pause");
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pausePage.fxml")));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        loadIslands();
        loadFallingPlatforms();
        interactables = new ArrayList<>();
        orcs = new ArrayList<>();


        for (Island island : islands) {
            Orc orc = OrcFactory.createOrc("red", island);
            orcs.add(orc);
            mainAnchorPane.getChildren().add(orc.getNode());
        }

        for (Orc orc : orcs) {
            (new OrcJumpGravityHandler(orc, islands)).start();
        }

        interactables.addAll(islands);
        interactables.addAll(fallingPlatforms);
        interactables.addAll(orcs);

        hero_obj = new Hero(this.hero);


        (new ScreenScroller(mainAnchorPane, 0.5)).start();
        (new ScreenScroller(cloudAnchorPane, 0.2)).start();
        (new JumpableFallChecker(hero_obj, deathZone)).start();
        (new HeroInteractChecker(hero_obj, interactables)).start();


    }

    private void loadIslands() {
        islands = new ArrayList<>();
        islands.add(new Island(island1));
        islands.add(new Island(island2));
        islands.add(new Island(island3));
        islands.add(new Island(island4));
    }

    private void loadFallingPlatforms() {
        fallingPlatforms = new ArrayList<>();
        fallingPlatforms.add(new FallingPlatform(platform1));
        fallingPlatforms.add(new FallingPlatform(platform2));
    }


    @FXML
    void moveForward(MouseEvent event) throws FileNotFoundException {
        hero_obj.moveForward();
    }

    public static void setStage(Stage _stage) {
        stage = _stage;
    }

    public void hit(ImageView rectangle, double delay) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(rectangle.rotateProperty(), 90,
                Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setDelay(Duration.seconds(delay));
        timeline.setAutoReverse(true);
        timeline.play();
    }


    @FXML
    public void game_over_handler(MouseEvent event) {
        game_over();
    }

    public static void game_over() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(GameController.class.getResource("gameOverPage.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
