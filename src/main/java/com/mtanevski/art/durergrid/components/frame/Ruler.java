package com.mtanevski.art.durergrid.components.frame;

import javafx.geometry.Orientation;
import javafx.scene.control.Slider;

public class Ruler extends Slider {

    private Ruler() {
        super();
        this.getStylesheets().add(getClass().getClassLoader().getResource("ruler.css").toExternalForm());
        this.setMin(0.0);
        this.setShowTickMarks(true);
        this.setShowTickLabels(true);
        this.setBlockIncrement(50.0);
        this.setMinorTickCount(10);
        this.setMajorTickUnit(50.0);
        this.setSnapToTicks(true);
    }

    public static Ruler horizontalRuler() {
        final Ruler ruler = new Ruler();
        ruler.setOrientation(Orientation.HORIZONTAL);
        ruler.getStyleClass().add("axis-top");
        return ruler;
    }

    public static Ruler verticalRuler() {
        final Ruler ruler = new Ruler();
        ruler.setOrientation(Orientation.VERTICAL);
        ruler.setScaleY(-1);
        ruler.getStyleClass().add("axis-left");
        return ruler;
    }

    public void setMax(Integer max) {
        super.setMax(max);
    }
}
