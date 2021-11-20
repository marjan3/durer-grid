package com.mtanevski.art.durergrid.components.grid;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InvisibleRectangle extends Rectangle {
    private static final double LINE_WIDTH = 0.0f;
    private final static Color COLOR = new Color(0.0f, 0.0f, 1.0f, 0.01f);

    public InvisibleRectangle(double fromX, double fromY) {
        super();
        this.setX(fromX);
        this.setY(fromY);
        this.setFill(COLOR);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(LINE_WIDTH);
    }

}
