package com.example.ap_project.fxml;

import java.io.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.ap_project.*;
import com.example.ap_project.animation_timers.*;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameController implements Serializable {
    private transient static Stage stage;
    private FileChooser fileChooser;

    private static GameController gameController;

    private int id;

    @FXML
    private transient Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private transient Pane hero;

    @FXML
    private transient Rectangle platform1 , platform2, platform3, platform4, platform5, platform6;

    @FXML
    private transient Region island1, island2, island3, island4, island5, island6, island7, island8, island9, island10, island11;

    @FXML
    private transient Rectangle deathZone;

    @FXML
    private transient AnchorPane mainAnchorPane;

    @FXML
    private transient AnchorPane cloudAnchorPane;

    @FXML
    private transient ImageView cannonShooter;

    @FXML
    private transient AnchorPane weaponAnchorPane;

    @FXML
    private transient Text distanceTextBox, coinsTextBox;

    @FXML
    private transient Rectangle victoryZone;

    private ArrayList<Interactable> interactables;

    private ArrayList<Island> islands;

    private ArrayList<FallingPlatform> fallingPlatforms;

    private ArrayList<Orc> orcs;

    private ArrayList<Chest> chests;

    private Hero hero_obj;
    private double anchorPaneLayoutX;

    private boolean isReady = false;

    public void setAnchorPaneLayoutX(double anchorPaneLayoutX) {
        System.out.println("Setting AnchorPaneLayoutX to " + anchorPaneLayoutX);
        this.anchorPaneLayoutX = anchorPaneLayoutX;
    }

    @FXML
    void pause(MouseEvent event) throws IOException {
        id +=1;
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();

        System.out.println("Pause");
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pausePage.fxml")));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void setInstance(GameController gameController) {
        GameController.gameController = gameController;
    }

    @FXML
    void initialize() throws IllegalArgumentException {
        if (hero_obj == null) {
            Random rand = new Random();
            id = rand.nextInt(1000);
            this.gameController = this;
            hero_obj = new Hero(this.hero, weaponAnchorPane);

            loadIslands();
            loadFallingPlatforms();
            interactables = new ArrayList<>();
            orcs = new ArrayList<>();
            chests = new ArrayList<>();




            for (Island island : islands) {
                Coin coin = Coin.insert(island);
                if (coin != null) {
                    interactables.add(coin);
                }
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

            for (Orc orc : orcs) {
                (new OrcJumpGravityHandler(orc, islands, id)).start();
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
        }
        else
            rehydrate();


        (new ScreenScroller(mainAnchorPane, 1, id)).start();
        (new ScreenScroller(cloudAnchorPane, 0.2, id)).start();
        (new JumpableFallChecker(hero_obj, deathZone, id)).start();
        (new JumpableWinChecker(hero_obj, victoryZone, id)).start();
        (new HeroInteractChecker(hero_obj, interactables, id)).start();
        (new DistanceCoinsUpdater(distanceTextBox, coinsTextBox, hero_obj)).start();

        isReady = true;


    }

    void rehydrate() {
        System.out.println("Rehydrating");
        System.out.println("Hero: " + anchorPaneLayoutX);
//        mainAnchorPane.setLayoutX(anchorPaneLayoutX);
        System.out.println("Hero2: " + anchorPaneLayoutX);
//        new Timeline(new KeyFrame(Duration.millis(1000), e -> hero_obj.getNode().setLayoutX(anchorPaneLayoutX)));
        Random rand = new Random();
        id = rand.nextInt(1000);
        for (Interactable interactable : interactables) {
            interactable.rehydrate(mainAnchorPane);
        }
        islands.get(0).setNode(island1);
        islands.get(1).setNode(island2);
        islands.get(2).setNode(island3);
        islands.get(3).setNode(island4);
        islands.get(4).setNode(island5);
        islands.get(5).setNode(island6);
        islands.get(6).setNode(island7);
        islands.get(7).setNode(island8);
        islands.get(8).setNode(island9);
        islands.get(9).setNode(island10);
        islands.get(10).setNode(island11);
        fallingPlatforms.get(0).setNode(platform1);
        fallingPlatforms.get(1).setNode(platform2);
        fallingPlatforms.get(2).setNode(platform3);
        fallingPlatforms.get(3).setNode(platform4);
        fallingPlatforms.get(4).setNode(platform5);
        fallingPlatforms.get(5).setNode(platform6);
        hero_obj.setWeaponPane(weaponAnchorPane);
        hero_obj.setNode(hero);
        hero_obj.rehydrate(mainAnchorPane);
        ThrowingWeaponHandler.setOrcs(orcs);

//        for (Orc orc : orcs) {
//            orc.rehydrate(mainAnchorPane);
//        }
//        for (Chest chest : chests) {
//            chest.rehydrate(mainAnchorPane);
//        }

    }

    public static GameController getInstance() {
        return gameController;
    }

    public int getId() {
        return id;
    }

    private void loadIslands() {
        islands = new ArrayList<>();
        islands.add(new Island(island1));
        islands.add(new Island(island2));
        islands.add(new Island(island3));
        islands.add(new Island(island4));
        islands.add(new Island(island5));
        islands.add(new Island(island6));
        islands.add(new Island(island7));
        islands.add(new Island(island8));
        islands.add(new Island(island9));
        islands.add(new Island(island10));
        islands.add(new Island(island11));
    }

    private void loadFallingPlatforms() {
        fallingPlatforms = new ArrayList<>();
        fallingPlatforms.add(new FallingPlatform(platform1));
        fallingPlatforms.add(new FallingPlatform(platform2));
        fallingPlatforms.add(new FallingPlatform(platform3));
        fallingPlatforms.add(new FallingPlatform(platform4));
        fallingPlatforms.add(new FallingPlatform(platform5));
        fallingPlatforms.add(new FallingPlatform(platform6));
    }


    @FXML
    void moveForward(MouseEvent event) throws FileNotFoundException {
        hero_obj.moveForward();
    }

    public static void setStage(Stage _stage) {
        stage = _stage;
    }

    public void serialize(GameController a) throws IOException {
        String myObj = String.valueOf(LocalTime.now());
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("out" + myObj.split("\\.")[1] + ".txt"))) {
            out.writeObject(a);
            System.out.println("Game saved");
        }
    }

    public void saveGame(GameController a) throws IOException {
        serialize(a);
    }

    public void deserialize() throws IOException, ClassNotFoundException {
        System.out.println("load clicked");
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"));
            File file = fileChooser.showOpenDialog(stage);
            //load file here
            try (ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file.getAbsoluteFile()))) {
                System.out.println(file.getPath());
                Object a = in.readObject();
                System.out.println("test" + a);
            }
    }

    public void loadGame() throws IOException, ClassNotFoundException {
        deserialize();
    }

    public ArrayList<Interactable> getInteractables(){
        return interactables;
    }

    public void game_over() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(GameController.class.getResource("gameOverPage.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        id=-1;

        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean isReady() {
        return isReady;
    }

    public void win() {
        System.out.println("play clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("win.fxml")));
        GameController gameController = new GameController();
        GameController.setInstance(gameController);
        loader.setController(gameController);

        try {
        scene = loader.load();}
        catch (IOException e) {
            e.printStackTrace();
        }
        //Move back a little to get a good view of the sphere
        GameController.setStage(stage);
//        ((GameController)(loader.getController())).setStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaxWidth(900);
        stage.setMaxHeight(480);
        stage.show();
    }
}
