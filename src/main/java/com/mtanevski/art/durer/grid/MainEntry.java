package com.mtanevski.art.durer.grid;

import com.mtanevski.art.durer.grid.components.RootContainer;
import com.mtanevski.art.durer.grid.components.frame.MainButtons;
import com.mtanevski.art.durer.grid.components.frame.Ruler;
import com.mtanevski.art.durer.grid.components.grid.Grid;
import com.mtanevski.art.durer.grid.components.preferences.PreferencesController;
import com.mtanevski.art.durer.grid.repos.PreferencesRepository;
import com.mtanevski.art.durer.grid.utils.FxUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;

import static com.mtanevski.art.durer.grid.utils.FxUtil.bgColor;
import static com.mtanevski.art.durer.grid.utils.FxUtil.setupWindowDragging;
import static com.mtanevski.art.durer.grid.utils.FxUtil.wrapInAnchorPane;

public class MainEntry extends Application {

    public static final String TITLE = "Dürer Grid";
    public static final String PREFERENCES_FXML = "preferences.fxml";
    private static final Property<Color> frameColor = new SimpleObjectProperty<>();
    private static final Property<Integer> width = new SimpleObjectProperty<>();
    private static final Property<Integer> height = new SimpleObjectProperty<>();
    private static final Property<Color> windowColor = new SimpleObjectProperty<>();
    private final Property<Number> frameLength = new SimpleObjectProperty<>(0.0f);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        /*
         *  SETUP SCENE
         */
        RootContainer root = new RootContainer();

        final Scene scene = new Scene(root);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        // canvas
        Grid grid = new Grid();
        root.add(wrapInAnchorPane(grid, 1.0), 1, 1, 1, 1);
        // buttons
        MainButtons mainButtons = new MainButtons();
        root.add(mainButtons, 2, 0, 1, 1);
        // frames
        final AnchorPane frame1 = new AnchorPane();
        root.add(frame1, 0, 0, 1, 1);
        final AnchorPane frame2 = new AnchorPane();
        root.add(frame2, 0, 2, 1, 1);
        final AnchorPane frame3 = new AnchorPane();
        root.add(frame3, 1, 2, 1, 1);
        final AnchorPane frame4 = new AnchorPane();
        root.add(frame4, 2, 2, 1, 1);
        final AnchorPane frame5 = new AnchorPane();
        root.add(frame5, 2, 1, 1, 1);
        // horizontal ruler
        Ruler horizontalSlider = Ruler.horizontalRuler();
        AnchorPane hAnchorPane = wrapInAnchorPane(horizontalSlider, null, 0.0, -5.0, -5.0);
        root.add(hAnchorPane, 1, 0, 1, 1);
        // vertical ruler
        Ruler verticalRuler = Ruler.verticalRuler();
        AnchorPane vAnchorPane = wrapInAnchorPane(verticalRuler, -5.0, -5.0, null, 0.0);
        root.add(vAnchorPane, 0, 1, 1, 1);

        /*
         * SETUP BINDINGS
         */
        final PreferencesController preferencesController = new PreferencesController();

        horizontalSlider.visibleProperty().bindBidirectional(verticalRuler.visibleProperty());

        grid.increment().bindBidirectional(horizontalSlider.valueProperty());
        grid.increment().bindBidirectional(verticalRuler.valueProperty());

        mainButtons.setOnClosed((e) -> Platform.exit());
        mainButtons.setOnOpenSettings(e -> {
            FxUtil.loadUtilWindow(PREFERENCES_FXML, preferencesController, scene.getWindow());
            preferencesController.bind(
                    horizontalSlider.visibleProperty(),
                    width,
                    height,
                    frameColor,
                    grid.gridLinesColor(),
                    grid.centerLinesColor(),
                    grid.gridLinesWidth(),
                    grid.centerLinesWidth(),
                    frameLength);
        });
        width.addListener((observable, oldValue, newValue) -> {
            final double length = root.getFrameLength();
            primaryStage.setWidth(newValue + (length * 2));
            grid.changeWidth(newValue);
            horizontalSlider.setMax(newValue);
        });
        height.addListener((observable, oldValue, newValue) -> {
            final double length = root.getFrameLength();
            primaryStage.setHeight(newValue + (length * 2));
            grid.changeHeight(newValue);
            verticalRuler.setMax(newValue);
        });
        frameColor.addListener((observable, oldValue, newValue) -> {
            Arrays.asList(
                    mainButtons,
                    hAnchorPane,
                    vAnchorPane,
                    frame1, frame2, frame3, frame4, frame5,
                    horizontalSlider, verticalRuler)
                    .forEach(n -> n.setStyle(bgColor(newValue)));
        });
        frameLength.addListener((observable, oldValue, newValue) -> {
            final double newDouble = newValue.doubleValue();
            final double oldDoubleValue = oldValue.doubleValue();
            final double difference = (oldDoubleValue - newDouble);
            final long newWidth = Math.max(DefaultValues.MIN_WIDTH, Math.round(grid.getWidth() + (difference * 2)));
            final long newHeight = Math.max(DefaultValues.MIN_HEIGHT, Math.round(grid.getHeight() + (difference * 2)));
            root.setFrameLength(newDouble);
            width.setValue((int) newWidth);
            height.setValue((int) newHeight);
        });
        windowColor.addListener((observable, oldValue, newValue) -> {
            scene.setFill(newValue);
            root.setStyle(bgColor(newValue));
        });

        setupWindowDragging(Arrays.asList(
                grid.getInteractive(),
                mainButtons,
                hAnchorPane, vAnchorPane,
                frame1, frame2, frame3, frame4, frame5), primaryStage);

        preferencesController.setOnPreferences(() -> {
            frameLength.setValue(PreferencesRepository.getFrameLength());
            width.setValue(PreferencesRepository.getWidth());
            height.setValue(PreferencesRepository.getHeight());
            grid.increment().setValue(DefaultValues.INCREMENT);
            frameColor.setValue(PreferencesRepository.getFrameColor());
            grid.gridLinesColor().setValue(PreferencesRepository.getGridLinesColor());
            grid.centerLinesColor().setValue(PreferencesRepository.getCenterLinesColor());
            grid.gridLinesWidth().setValue(PreferencesRepository.getGridLinesWidth());
            grid.centerLinesWidth().setValue(PreferencesRepository.getCenterLinesWidth());
            windowColor.setValue(DefaultValues.WINDOW_COLOR);
        });

        /*
         * FINAl
         */
        primaryStage.show();
        preferencesController.resetPreferences();

    }

}
