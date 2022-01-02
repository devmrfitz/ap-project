package com.example.ap_project;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public final class Utility {
    private Utility() {
    }

    public static boolean intersects(Node s1, Node s2) {
        Bounds bounds = s2.getBoundsInParent();
        return s1.getBoundsInParent().intersects(bounds);
    }

    public static int intersectDirection(Node s1, Node s2) {
        Bounds bounds = s2.getBoundsInParent();
        Bounds bounds1 = s1.getBoundsInParent();
        if (bounds.getMinX() < bounds1.getMinX() && bounds.getMaxX() > bounds1.getMaxX()) {
            return 0;
        } else if (bounds.getMinY() < bounds1.getMinY() && bounds.getMaxY() > bounds1.getMaxY()) {
            return 1;
        } else if (bounds.getMinX() < bounds1.getMinX() && bounds.getMaxX() > bounds1.getMinX()) {
            return 2;
        } else if (bounds.getMinY() < bounds1.getMinY() && bounds.getMaxY() > bounds1.getMaxY()) {
            return 3;
        }
        return -1;
    }
}
