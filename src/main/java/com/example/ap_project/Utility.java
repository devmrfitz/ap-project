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
}
