package com.example.ap_project;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.Random;

public class Coin extends GameObject {
    private static final Image image = new Image("file:src/main/resources/com/example/ap_project/images/Coin.png");
    private static final double height = 30, width = 30;
    private static final Random random = new Random();

    private Coin(ImageView imageView) {
        super(imageView);
    }

    public static Coin insert(Island island) {
        if (random.nextBoolean()) {
            Bounds bounds = island.getNode().getBoundsInParent();
            double islandWidth = (((Region) island.getNode()).getPrefWidth());
            double x = (2 * bounds.getMaxX() + width) / 2 + (random.nextInt(3) - 1) * 20;
            double y = bounds.getMinY() + (random.nextInt(3) ) * 20;

            ImageView img = new ImageView(image);
            img.setFitHeight(height);
            img.setFitWidth(width);
            img.setLayoutX(x + islandWidth / 2);
            img.setLayoutY(y - height);
            Coin coin = new Coin(img);
            ((AnchorPane) island.getNode().getParent()).getChildren().add(coin.getNode());
            return coin;
        } else {
            System.out.println("No coin spawned");
            return null;
        }
    }

    @Override
    public void interact(int interaction, Hero hero) {
        hero.addCoins(1);
        deSpawn();
    }

    public void deSpawn() {
        getNode().setVisible(false);
        getNode().setLayoutX(-10);
        setExists(false);
        if (getNode().getParent() != null)
            ((AnchorPane) getNode().getParent()).getChildren().remove(getNode());
    }

    @Override
    public void rehydrate(Node _mainAnchorPane) {
        if (exists()) {
            AnchorPane mainAnchorPane = (AnchorPane) _mainAnchorPane;
            ImageView img = new ImageView(image);
            img.setFitHeight(height);
            img.setFitWidth(width);
            img.setLayoutX(getPosition().getKey());
            img.setLayoutY(getPosition().getValue());
            Coin coin = new Coin(img);
            mainAnchorPane.getChildren().add(coin.getNode());
        }
    }
}
