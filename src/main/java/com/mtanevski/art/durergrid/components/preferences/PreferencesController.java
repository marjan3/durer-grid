package com.mtanevski.art.durergrid.components.preferences;

import com.mtanevski.art.durergrid.repos.PreferencesRepository;
import com.mtanevski.art.durergrid.DefaultValues;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;

public class PreferencesController {

    @FXML
    public ColorPicker gridLinesColor;
    @FXML
    public ColorPicker frameColor;
    @FXML
    public ColorPicker centerLinesColor;
    @FXML
    public Slider frameLength;
    @FXML
    public Slider gridLinesWidth;
    @FXML
    public Slider centerLinesWidth;
    @FXML
    public Spinner<Integer> height;
    @FXML
    public Spinner<Integer> width;
    @FXML
    public CheckBox showRulers;

    private Runnable onResetPreferences;

    @FXML
    public void initialize() {
        width.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(DefaultValues.MIN_WIDTH, DefaultValues.MAX_WIDTH));
        height.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(DefaultValues.MIN_HEIGHT, DefaultValues.MAX_HEIGHT));
        frameLength.setMin(DefaultValues.MIN_FRAME_LENGTH);
        frameLength.setMax(DefaultValues.MAX_FRAME_LENGTH);
        gridLinesWidth.setMin(DefaultValues.MIN_LINES_WIDTH);
        gridLinesWidth.setMax(DefaultValues.MAX_LINES_WIDTH);
        centerLinesWidth.setMin(DefaultValues.MIN_LINES_WIDTH);
        centerLinesWidth.setMax(DefaultValues.MAX_LINES_WIDTH);

        this.width.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setWidth(newValue));
        this.height.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setHeight(newValue));
        this.frameColor.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setFrameColor(newValue));
        this.gridLinesColor.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setGridLinesColor(newValue));
        this.centerLinesColor.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setCenterLinesColor(newValue));
        this.centerLinesWidth.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setCenterLinesWidth(newValue.doubleValue()));
        this.gridLinesWidth.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setGridLinesWidth(newValue.doubleValue()));
        this.frameLength.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setFrameLength(newValue.intValue()));
    }

    public void onResetPreferences(Runnable runnable) {
        this.onResetPreferences = runnable;
    }

    public void bind(Property<Boolean> showRules,
                     Property<Integer> width,
                     Property<Integer> height,
                     Property<Color> frameColor,
                     Property<Color> gridColor,
                     Property<Color> centerLinesColor,
                     Property<Number> gridLinesWidth,
                     Property<Number> centerLinesWidth,
                     Property<Number> frameLength) {
        this.showRulers.selectedProperty().bindBidirectional(showRules);
        this.width.getValueFactory().valueProperty().bindBidirectional(width);
        this.height.getValueFactory().valueProperty().bindBidirectional(height);
        this.frameColor.valueProperty().bindBidirectional(frameColor);
        this.gridLinesColor.valueProperty().bindBidirectional(gridColor);
        this.centerLinesColor.valueProperty().bindBidirectional(centerLinesColor);
        this.gridLinesWidth.valueProperty().bindBidirectional(gridLinesWidth);
        this.centerLinesWidth.valueProperty().bindBidirectional(centerLinesWidth);
        this.frameLength.valueProperty().bindBidirectional(frameLength);
    }

    @FXML
    public void resetPreferences() {
        onResetPreferences.run();
    }
}
