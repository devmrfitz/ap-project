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
        if (intersects(s1, s2)) {
            return collisionAxis(s1, s2) + intersectDirectionX(s1, s2) + intersectDirectionY(s1, s2);
        }
        else
            return -1;
    }


    private static int intersectDirectionX(Node heroNode, Node s2) {
        assert intersects(heroNode, s2);
        Bounds bounds = s2.getBoundsInParent();
        Bounds heroBounds = heroNode.getBoundsInParent();
        if (bounds.getCenterX() >= heroBounds.getCenterX()) {
            return 1;
        }
        else
            return 0;
    }

    private static int intersectDirectionY(Node heroNode, Node s2) {
        assert intersects(heroNode, s2);
        Bounds bounds = s2.getBoundsInParent();
        Bounds heroBounds = heroNode.getBoundsInParent();
        if (bounds.getCenterY() >= heroBounds.getCenterY()) {
            return 2;
        }
        else
            return 0;
    }

    private static int collisionAxis(Node heroNode, Node s2) {
        // 16 means collision along Y axis
        assert intersects(heroNode, s2);
        Bounds bounds = s2.getBoundsInParent();
        Bounds heroBounds = heroNode.getBoundsInParent();
        if (Math.abs(bounds.getCenterX()- heroBounds.getCenterX()) <
                Math.abs(bounds.getCenterY()- heroBounds.getCenterY())) {
            return 4;
        }
        else {
            return 0;
        }
    }

    public static boolean checkIthBit(int n, int i)
    {
        return ((n & (1 << (i - 1))) > 0);
    }
}
