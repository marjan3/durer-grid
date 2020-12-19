package com.mtanevski.art.durer.grid.utils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FxUtil {

    public static Node loadUtilWindow(String location, Object controller, Window window) {
        FXMLLoader fxmlLoader = new FXMLLoader(controller.getClass().getClassLoader().getResource(location));
        fxmlLoader.setController(controller);

        try {
            final Parent load = fxmlLoader.load();
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(window);
            stage.setScene(scene);
            stage.show();

            return load;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setupWindowDragging(List<Node> nodes, Stage stage) {
        nodes.forEach(n -> {
            AtomicReference<Point2D> stageOffset = new AtomicReference<>();
            n.setOnMousePressed(event -> {
                final double x = stage.getX() - event.getScreenX();
                final double y = stage.getY() - event.getScreenY();
                stageOffset.set(new Point2D(x, y));
                n.getScene().setCursor(Cursor.CLOSED_HAND);
            });

            n.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() + stageOffset.get().getX());
                stage.setY(event.getScreenY() + stageOffset.get().getY());
            });

            n.setOnMouseReleased(event -> n.getScene().setCursor(Cursor.DEFAULT));
        });
    }

    public static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));

        return String.format("#%08X", (r + g + b + a));
    }

    public static String bgColor(Color color) {
        return "-fx-background-color: " + toHexString(color) + ";";
    }

    public static AnchorPane wrapInAnchorPane(Node node, double anchor) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, anchor);
        AnchorPane.setLeftAnchor(node, anchor);
        AnchorPane.setRightAnchor(node, anchor);
        AnchorPane.setBottomAnchor(node, anchor);
        return anchorPane;
    }

    public static AnchorPane wrapInAnchorPane(Node node, Double anchorTop, Double anchorBottom, Double anchorLeft, Double anchorRight) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, anchorTop);
        AnchorPane.setBottomAnchor(node, anchorBottom);
        AnchorPane.setLeftAnchor(node, anchorLeft);
        AnchorPane.setRightAnchor(node, anchorRight);
        return anchorPane;
    }

}
