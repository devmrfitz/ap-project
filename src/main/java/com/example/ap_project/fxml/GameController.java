package com.example.ap_project.fxml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.ap_project.*;
import com.example.ap_project.animation_timers.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Region island1, island2, island3, island4;

    @FXML
    private ImageView weapon;

    @FXML
    private Rectangle deathZone;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane cloudAnchorPane;

    @FXML
    private ImageView cannonShooter;

    @FXML
    private AnchorPane weaponAnchorPane;

    private ArrayList<Interactable> interactables;

    private ArrayList<Island> islands;

    private ArrayList<FallingPlatform> fallingPlatforms;

    private ArrayList<Orc> orcs;

    private ArrayList<Chest> chests;

    private Hero hero_obj;

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
    void initialize() throws IllegalArgumentException {
        hero_obj = new Hero(this.hero, weaponAnchorPane);

        loadIslands();
        loadFallingPlatforms();
        interactables = new ArrayList<>();
        orcs = new ArrayList<>();
        chests = new ArrayList<>();

        Random rand = new Random();
        for (Island island : islands) {
            int num = rand.nextInt(5);
            switch (num) {
                case 0 -> {
                    Orc orc = Factory.createOrc("red", island);
                    orcs.add(orc);
                    mainAnchorPane.getChildren().add(orc.getNode());
                }
                case 1 -> {
                    Chest chest = Factory.createChest("coin", island);
                    chests.add(chest);
                    mainAnchorPane.getChildren().add(chest.getNode());
                }
                case 2 -> {
                    Orc orc = Factory.createOrc("green", island);
                    orcs.add(orc);
                    mainAnchorPane.getChildren().add(orc.getNode());
                }
                case 3 -> {
                    Orc orc = Factory.createOrc("boss", island);
                    orcs.add(orc);
                    mainAnchorPane.getChildren().add(orc.getNode());
                }
                case 4 -> {
                    Chest chest = Factory.createChest("weapon", island);
                    chests.add(chest);
                    mainAnchorPane.getChildren().add(chest.getNode());
                }
            }
        }

        Chest chest = Factory.createChest("weapon", islands.get(0));
        chests.add(chest);
        mainAnchorPane.getChildren().add(chest.getNode());

        chest = Factory.createChest("weapon", islands.get(0));
        chests.add(chest);
        mainAnchorPane.getChildren().add(chest.getNode());

        for (Orc orc : orcs) {
            (new OrcJumpGravityHandler(orc, islands)).start();
        }

        Cannon cannon = new Cannon(cannonShooter);

        Timeline timeline =
                new Timeline(new KeyFrame(Duration.millis(500), e -> cannon.shoot()));
        timeline.setCycleCount(Animation.INDEFINITE); // loop forever
        timeline.play();


        ThrowingWeaponHandler.setOrcs(orcs);

        interactables.addAll(islands);
        interactables.addAll(fallingPlatforms);
        interactables.addAll(orcs);
        interactables.addAll(chests);


        CannonHandler.setHero(hero_obj);


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
