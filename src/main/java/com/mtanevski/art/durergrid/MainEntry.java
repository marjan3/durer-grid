package com.mtanevski.art.durergrid;

import com.mtanevski.art.durergrid.components.RootContainer;
import com.mtanevski.art.durergrid.components.frame.MainButtons;
import com.mtanevski.art.durergrid.components.frame.Ruler;
import com.mtanevski.art.durergrid.components.grid.Grid;
import com.mtanevski.art.durergrid.components.preferences.PreferencesController;
import com.mtanevski.art.durergrid.repos.PreferencesRepository;
import com.mtanevski.art.durergrid.utils.FxUtil;
import com.mtanevski.art.durergrid.utils.Resizer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Arrays;

import static com.mtanevski.art.durergrid.DefaultValues.MAX_HEIGHT;
import static com.mtanevski.art.durergrid.DefaultValues.MAX_WIDTH;
import static com.mtanevski.art.durergrid.utils.FxUtil.wrapInAnchorPane;

public class MainEntry extends Application {

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
        var root = new RootContainer();

        final Scene scene = new Scene(root);
        primaryStage.setTitle(DefaultValues.TITLE);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Resizer.addResizeListener(primaryStage, width, height);
        // canvas
        var grid = new Grid();
        root.add(FxUtil.wrapInAnchorPane(grid, 1.0), 1, 1, 1, 1);
        // buttons
        root.setGridLinesVisible(true);
        var mainButtons = new MainButtons();
        root.add(mainButtons, 2, 0, 1, 1);

        // frames
        var frame1 = new AnchorPane();
        root.add(frame1, 0, 0, 1, 1);
        var frame2 = new AnchorPane();
        root.add(frame2, 0, 2, 1, 1);
        var frame3 = new AnchorPane();
        root.add(frame3, 1, 2, 1, 1);
        var frame4 = new AnchorPane();
        root.add(frame4, 2, 2, 1, 1);
        var frame5 = new AnchorPane();
        root.add(frame5, 2, 1, 1, 1);
        // horizontal ruler
        var horizontalSlider = Ruler.horizontalRuler();
        var hAnchorPane = FxUtil.wrapInAnchorPane(horizontalSlider, null, 0.0, -5.0, -5.0);
        root.add(hAnchorPane, 1, 0, 1, 1);
        // vertical ruler
        var verticalRuler = Ruler.verticalRuler();
        var vAnchorPane = FxUtil.wrapInAnchorPane(verticalRuler, -5.0, -5.0, null, 0.0);
        root.add(vAnchorPane, 0, 1, 1, 1);

        /*
         * SETUP BINDINGS
         */
        var preferencesController = new PreferencesController();

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
        mainButtons.setOnLoadImage(e -> {
            var fileChooser = new FileChooser();
            var file = fileChooser.showOpenDialog(scene.getWindow());
            if (file != null) {
                var image = new Image(file.toURI().toString());
                width.setValue(Integer.min((int)image.getWidth(), MAX_WIDTH));
                height.setValue(Integer.min((int)image.getHeight(), MAX_HEIGHT));
                var backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
                var backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
                grid.setBackground(new Background(backgroundImage));
            }
        });
        mainButtons.setOnClearImage(e -> grid.setBackground(null));
        width.addListener((o, oldValue, newValue) -> {
            var length = root.getFrameLength();
            primaryStage.setWidth(newValue + (length * 2));
            grid.changeWidth(newValue);
            horizontalSlider.setMax(newValue);
        });
        height.addListener((o, oldValue, newValue) -> {
            var length = root.getFrameLength();
            primaryStage.setHeight(newValue + (length * 2));
            grid.changeHeight(newValue);
            verticalRuler.setMax(newValue);
        });
        frameColor.addListener((o, oldValue, newValue) -> {
            Arrays.asList(
                    mainButtons,
                    hAnchorPane,
                    vAnchorPane,
                    frame1, frame2, frame3, frame4, frame5,
                    horizontalSlider, verticalRuler)
                    .forEach(n -> n.setStyle(FxUtil.bgColor(newValue)));
        });
        frameLength.addListener((o, oldValue, newValue) -> {
            final double newDouble = newValue.doubleValue();
            final double oldDoubleValue = oldValue.doubleValue();
            final double difference = (oldDoubleValue - newDouble);
            final long newWidth = Math.max(DefaultValues.MIN_WIDTH, Math.round(grid.getWidth() + (difference * 2)));
            final long newHeight = Math.max(DefaultValues.MIN_HEIGHT, Math.round(grid.getHeight() + (difference * 2)));
            root.setFrameLength(newDouble);
            width.setValue((int) newWidth);
            height.setValue((int) newHeight);
        });
        windowColor.addListener((o, oldValue, newValue) -> {
            scene.setFill(newValue);
            root.setStyle(FxUtil.bgColor(newValue));
        });

        FxUtil.setupWindowDragging(Arrays.asList(
                grid.getInteractive(),
                mainButtons,
                hAnchorPane, vAnchorPane,
                frame1, frame2, frame3, frame4, frame5), primaryStage);

        preferencesController.onResetPreferences(() -> {
            frameLength.setValue(DefaultValues.FRAME_LENGTH);
            width.setValue(DefaultValues.WIDTH);
            height.setValue(DefaultValues.HEIGHT);
            grid.increment().setValue(DefaultValues.INCREMENT);
            frameColor.setValue(DefaultValues.FRAME_COLOR);
            grid.gridLinesColor().setValue(DefaultValues.GRID_LINES_COLOR);
            grid.centerLinesColor().setValue(DefaultValues.CENTER_LINES_COLOR);
            grid.gridLinesWidth().setValue(DefaultValues.GRID_LINES_WIDTH);
            grid.centerLinesWidth().setValue(DefaultValues.CENTER_LINES_WIDTH);
            windowColor.setValue(DefaultValues.WINDOW_COLOR);
        });

        /*
         * FINAl
         */
        primaryStage.show();
        setInitialPreferences(grid);

    }

    private void setInitialPreferences(Grid grid) {
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
    }

}
