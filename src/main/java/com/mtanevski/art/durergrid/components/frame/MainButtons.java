package com.mtanevski.art.durergrid.components.frame;

import com.mtanevski.art.durergrid.DefaultValues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainButtons extends AnchorPane {

    private final SplitMenuButton otherButton;
    private final Button closeButton;
    private final MenuItem loadImageMenuItem;
    private final MenuItem clearImageMenuItem;
    private final MenuItem settingMenuItem;

    public MainButtons() {
        closeButton = new Button("x");
        loadImageMenuItem = new MenuItem("Load image...");
        clearImageMenuItem = new MenuItem("Clear image");
        settingMenuItem = new MenuItem("Settings...");
        otherButton = new SplitMenuButton(loadImageMenuItem, clearImageMenuItem, settingMenuItem);
        otherButton.setText("...");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(otherButton, closeButton);
        buttons.setSpacing(DefaultValues.BUTTONS_SPACING);
        setTopAnchor(buttons, DefaultValues.BUTTONS_SPACING);
        setRightAnchor(buttons, DefaultValues.BUTTONS_SPACING);
        this.getChildren().add(buttons);
    }

    public void setOnClosed(EventHandler<ActionEvent> onClose) {
        closeButton.setOnAction(onClose);
    }

    public void setOnOpenSettings(EventHandler<ActionEvent> onSettings) {
        settingMenuItem.setOnAction(onSettings);
    }

    public void setOnLoadImage(EventHandler<ActionEvent> onLoadImage) {
        loadImageMenuItem.setOnAction(onLoadImage);
    }

    public void setOnClearImage(EventHandler<ActionEvent> onClearImage) {
        clearImageMenuItem.setOnAction(onClearImage);
    }
}
