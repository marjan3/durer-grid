package com.mtanevski.art.durer.grid.components.preferences;

import com.mtanevski.art.durer.grid.repos.PreferencesRepository;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;

import static com.mtanevski.art.durer.grid.DefaultValues.MAX_FRAME_LENGTH;
import static com.mtanevski.art.durer.grid.DefaultValues.MAX_HEIGHT;
import static com.mtanevski.art.durer.grid.DefaultValues.MAX_LINES_WIDTH;
import static com.mtanevski.art.durer.grid.DefaultValues.MAX_WIDTH;
import static com.mtanevski.art.durer.grid.DefaultValues.MIN_FRAME_LENGTH;
import static com.mtanevski.art.durer.grid.DefaultValues.MIN_HEIGHT;
import static com.mtanevski.art.durer.grid.DefaultValues.MIN_LINES_WIDTH;
import static com.mtanevski.art.durer.grid.DefaultValues.MIN_WIDTH;

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
        width.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_WIDTH, MAX_WIDTH));
        height.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_HEIGHT, MAX_HEIGHT));
        frameLength.setMin(MIN_FRAME_LENGTH);
        frameLength.setMax(MAX_FRAME_LENGTH);
        gridLinesWidth.setMin(MIN_LINES_WIDTH);
        gridLinesWidth.setMax(MAX_LINES_WIDTH);
        centerLinesWidth.setMin(MIN_LINES_WIDTH);
        centerLinesWidth.setMax(MAX_LINES_WIDTH);

        this.width.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setWidth(newValue));
        this.height.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setHeight(newValue));
        this.frameColor.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setFrameColor(newValue));
        this.gridLinesColor.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setGridLinesColor(newValue));
        this.centerLinesColor.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setCenterLinesColor(newValue));
        this.centerLinesWidth.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setCenterLinesWidth(newValue.doubleValue()));
        this.gridLinesWidth.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setGridLinesWidth(newValue.doubleValue()));
        this.frameLength.valueProperty().addListener((observable, oldValue, newValue) -> PreferencesRepository.setFrameLength(newValue.intValue()));
    }

    public void setOnPreferences(Runnable runnable) {
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
