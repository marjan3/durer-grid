package com.mtanevski.art.durer.grid.components.frame;

import com.mtanevski.art.durer.grid.DefaultValues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainButtons extends AnchorPane {

    private final Button settingsButton;
    private final Button closeButton;

    public MainButtons() {
        closeButton = new Button("x");
        settingsButton = new Button("...");
        HBox buttons = new HBox();
        buttons.setSpacing(DefaultValues.BUTTONS_SPACING);
        buttons.getChildren().addAll(settingsButton, closeButton);
        setTopAnchor(buttons, DefaultValues.BUTTONS_SPACING);
        setRightAnchor(buttons, DefaultValues.BUTTONS_SPACING);
        this.getChildren().add(buttons);
    }

    public void setOnClosed(EventHandler<ActionEvent> onClose) {
        closeButton.setOnAction(onClose);
    }

    public void setOnOpenSettings(EventHandler<ActionEvent> onSettings) {
        settingsButton.setOnAction(onSettings);
    }
}
