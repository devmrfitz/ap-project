package com.example.ap_project;

import javafx.scene.Node;

public interface NodeComposer {
    Node getNode();

    default boolean exists() {
        return true;
    }

    void setNode(Node node);

    void rehydrate(Node node);
}
