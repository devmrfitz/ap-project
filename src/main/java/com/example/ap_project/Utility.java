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

    // -1 -> No intersection
    // 0 -> hero is left-below
    // 1 -> hero is to right-below
    // 2 -> hero is to left-above
    // 3 -> hero is to right-above

    public static int intersectDirection(Node s1, Node s2) {
        if (intersects(s1, s2)) {
            return intersectDirectionX(s1, s2) + intersectDirectionY(s1, s2);
        }
        else
            return -1;
    }


    public static int intersectDirectionX(Node s1, Node s2) {
        if (intersects(s1, s2)) {
            Bounds bounds = s2.getBoundsInParent();
            Bounds bounds1 = s1.getBoundsInParent();
            if (bounds.getCenterX() < bounds1.getCenterX()) {
                return 4;
            } else if (bounds.getCenterX() > bounds1.getCenterX()) {
                return 8;
            }
            return 0;

        }
        else
            return -1;
    }

    public static int intersectDirectionY(Node heroNode, Node s2) {
        if (intersects(heroNode, s2)) {
            Bounds bounds = s2.getBoundsInParent();
            Bounds heroBounds = heroNode.getBoundsInParent();
            //print max and min
            System.out.println("min: " + bounds.getMinY() + " max: " + bounds.getMaxY());
            System.out.println("min: " + heroBounds.getMinY() + " max: " + heroBounds.getMaxY());
            if (bounds.getMaxY() <= heroBounds.getMinY() + 5) {
                return 1;
            } else if (bounds.getMinY() + 5 >= heroBounds.getMaxY()) {
                return 2;
            }
            return 0;
        }
        else
            return -1;
    }

    public static boolean checkIthBit(int n, int i)
    {
        return ((n & (1 << (i - 1))) > 0);
    }
}
