package com.example.ap_project;

import javafx.geometry.Bounds;
import javafx.scene.layout.Region;

public class Factory {

    public static Orc createOrc(String type, Island island) {
        Bounds bounds = island.getNode().getBoundsInParent();
        double width= (((Region)island.getNode()).getPrefWidth());
        double x=(2*bounds.getMaxX()+width)/2;
        double y=bounds.getMinY();

        switch (type) {
            case "red" -> {
                return StandardRedOrc.getInstance(x, y);
            }
            case "green" -> {
                return StandardGreenOrc.getInstance(x, y);
            }
            case "boss" -> {
                return Boss.getInstance(x, y);
            }
        }
        throw new IllegalArgumentException("No such type of orc");
    }

    public static Chest createChest(String type, Island island) {
        Bounds bounds = island.getNode().getBoundsInParent();
        double width= (((Region)island.getNode()).getPrefWidth());
        double x=(2*bounds.getMaxX()+width)/2;
        double y=bounds.getMinY();

        switch (type) {
            case "coin" -> {
                return CoinChest.getInstance(x, y);
            }
            case "weapon" -> {
//                return StandardGreenOrc.getInstance(x, y);
            }
        }
        throw new IllegalArgumentException("No such type of orc");
    }
}
