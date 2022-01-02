package com.example.ap_project;
import javafx.util.Pair;

public interface Positionable extends NodeComposer {
    public Pair<Double,Double> getPosition();
    public void setPosition(Pair<Double,Double> position);

    void savePosition();
}
