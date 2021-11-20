package com.mtanevski.art.durergrid.components.grid;

import com.mtanevski.art.durergrid.DefaultValues;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid extends Pane {

    public static final Point2D START = new Point2D(0.0, 0.0);
    private final InvisibleRectangle interactiveRectangle;
    private final Group linesContainer;
    private final Group centerLinesContainer;
    private final Property<Number> increment = new SimpleObjectProperty<>(0.0d);
    private final Property<Number> centerLinesWidth = new SimpleObjectProperty<>();
    private final Property<Number> gridLinesWidth = new SimpleObjectProperty<>();
    private final Property<Color> gridLinesColor = new SimpleObjectProperty<>();
    private final Property<Color> centerLinesColor = new SimpleObjectProperty<>();


    public Grid() {
        interactiveRectangle = new InvisibleRectangle(START.getX(), START.getY());
        linesContainer = new Group();
        centerLinesContainer = new Group();
        this.getChildren().addAll(interactiveRectangle, linesContainer, centerLinesContainer);
        this.increment.addListener((observable, oldValue, newValue) -> {
            this.drawGridAsync(this.increment.getValue().doubleValue());
        });
        gridLinesColor.addListener((observable, oldValue, newValue) -> {
            this.getGridLines().forEach(s -> {
                s.setStroke(newValue);
            });
        });
        centerLinesColor.addListener((observable, oldValue, newValue) -> {
            this.getCenterLines().forEach(s -> {
                s.setStroke(newValue);
            });
        });
        gridLinesWidth.addListener((observable, oldValue, newValue) -> {
            this.getGridLines().forEach(s -> {
                s.setStrokeWidth(newValue.doubleValue());
            });
        });
        centerLinesWidth.addListener((observable, oldValue, newValue) -> {
            this.getCenterLines().forEach(s -> {
                s.setStrokeWidth(newValue.doubleValue());
            });
        });
    }

    public void changeSize(double newWidth, double newHeight) {
        this.setMinWidth(newWidth);
        this.setMaxWidth(newWidth);
        this.setWidth(newWidth);
        this.setMinHeight(newHeight);
        this.setMaxHeight(newHeight);
        this.setHeight(newHeight);
        this.drawGridAsync(this.increment.getValue().doubleValue());
    }

    public void changeWidth(double newWidth) {
        this.setMinWidth(newWidth);
        this.setMaxWidth(newWidth);
        this.setWidth(newWidth);
        this.drawGridAsync(this.increment.getValue().doubleValue());
    }

    public void changeHeight(double newHeight) {
        this.setMinHeight(newHeight);
        this.setMaxHeight(newHeight);
        this.setHeight(newHeight);
        this.drawGridAsync(this.increment.getValue().doubleValue());
    }

    public Rectangle getInteractive() {
        return this.interactiveRectangle;
    }

    public List<Line> getGridLines() {
        return this.linesContainer.getChildren().stream().map(n -> (Line) n).collect(Collectors.toList());
    }

    public List<Line> getCenterLines() {
        return this.centerLinesContainer.getChildren().stream().map(n -> (Line) n).collect(Collectors.toList());
    }

    public Property<Number> increment() {
        return increment;
    }

    public Property<Color> gridLinesColor() {
        return gridLinesColor;
    }

    public Property<Color> centerLinesColor() {
        return centerLinesColor;
    }

    public Property<Number> gridLinesWidth() {
        return gridLinesWidth;
    }

    public Property<Number> centerLinesWidth() {
        return centerLinesWidth;
    }

    private void drawGridAsync(double newValue) {
        Platform.runLater(() -> {
            interactiveRectangle.setWidth(this.getWidth());
            interactiveRectangle.setHeight(this.getHeight());
            drawGridLines(Math.max(newValue, DefaultValues.MIN_INCREMENT));
            drawCenterLines();
        });
    }

    private void drawGridLines(double increment) {
        double width = getWidth() + 1;
        double height = getHeight() + 1;
        linesContainer.getChildren().clear();
        List<Node> children = new ArrayList<>();
        final Number gridLinesWidth = this.gridLinesWidth.getValue();

        // draw horizontal lines X
        for (double y = START.getY(); y <= height; y += increment) {
            final Line horizontalLine = new Line(START.getX(), y, width, y);
            horizontalLine.setStroke(this.gridLinesColor.getValue());
            if (gridLinesWidth != null) {
                horizontalLine.setStrokeWidth(gridLinesWidth.doubleValue());
            }
            children.add(horizontalLine);
        }

        // draw vertical lines Y
        for (double x = START.getX(); x <= width; x += increment) {
            final Line verticalLine = new Line(x, START.getY(), x, height);
            verticalLine.setStroke(this.gridLinesColor.getValue());
            if (gridLinesWidth != null) {
                verticalLine.setStrokeWidth(gridLinesWidth.doubleValue());
            }
            children.add(verticalLine);
        }

        linesContainer.getChildren().addAll(children);

    }

    private void drawCenterLines() {
        double width = getWidth();
        double height = getHeight();
        centerLinesContainer.getChildren().clear();
        List<Node> children = new ArrayList<>();

        // draw horizontal line
        final Line horizontalLine = new Line(START.getX(), height / 2, width, height / 2);
        horizontalLine.setStroke(this.centerLinesColor.getValue());
        final Number value = this.centerLinesWidth.getValue();
        if (value != null) {
            horizontalLine.setStrokeWidth(value.doubleValue());
        }
        children.add(horizontalLine);
        // draw vertical line
        final Line verticalLine = new Line(width / 2, START.getY(), width / 2, height);
        verticalLine.setStroke(this.centerLinesColor.getValue());
        if (value != null) {
            verticalLine.setStrokeWidth(value.doubleValue());
        }
        children.add(verticalLine);

        centerLinesContainer.getChildren().addAll(children);
    }

}

