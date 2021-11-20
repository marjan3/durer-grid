package com.mtanevski.art.durergrid.repos;

import com.mtanevski.art.durergrid.DefaultValues;
import com.mtanevski.art.durergrid.utils.FxUtil;
import javafx.scene.paint.Color;

import java.util.prefs.Preferences;

public class PreferencesRepository {

    private static final Preferences PREFERENCES = Preferences.userNodeForPackage(PreferencesRepository.class);
    private static final String WIDTH = "WIDTH";
    private static final String HEIGHT = "HEIGHT";
    private static final String FRAME_COLOR = "FRAME_COLOR";
    private static final String GRID_LINES_COLOR = "GRID_LINES_COLOR";
    private static final String CENTER_LINES_COLOR = "CENTER_LINES_COLOR";
    private static final String GRID_LINES_WIDTH = "GRID_LINES_WIDTH";
    private static final String CENTER_LINES_WIDTH = "CENTER_LINES_WIDTH";
    private static final String FRAME_LENGTH = "FRAME_LENGTH";

    public static int getWidth() {
        return PREFERENCES.getInt(WIDTH, DefaultValues.WIDTH);
    }

    public static void setWidth(int value) {
        PREFERENCES.putInt(WIDTH, value);
    }

    public static int getHeight() {
        return PREFERENCES.getInt(HEIGHT, DefaultValues.HEIGHT);
    }

    public static void setHeight(int value) {
        PREFERENCES.putInt(HEIGHT, value);
    }

    public static Color getFrameColor() {
        return Color.valueOf(PREFERENCES.get(FRAME_COLOR, FxUtil.toHexString(DefaultValues.FRAME_COLOR)));
    }

    public static void setFrameColor(Color value) {
        PREFERENCES.put(HEIGHT, FxUtil.toHexString(value));
    }

    public static Color getGridLinesColor() {
        return Color.valueOf(PREFERENCES.get(GRID_LINES_COLOR, FxUtil.toHexString(DefaultValues.GRID_LINES_COLOR)));
    }

    public static void setGridLinesColor(Color value) {
        PREFERENCES.put(GRID_LINES_COLOR, FxUtil.toHexString(value));
    }

    public static Color getCenterLinesColor() {
        return Color.valueOf(PREFERENCES.get(CENTER_LINES_COLOR, FxUtil.toHexString(DefaultValues.CENTER_LINES_COLOR)));
    }

    public static void setCenterLinesColor(Color value) {
        PREFERENCES.put(CENTER_LINES_COLOR, FxUtil.toHexString(value));
    }

    public static void setGridLinesWidth(double value) {
        PREFERENCES.putDouble(GRID_LINES_WIDTH, value);
    }

    public static double getGridLinesWidth() {
        return PREFERENCES.getDouble(GRID_LINES_WIDTH, DefaultValues.GRID_LINES_WIDTH);
    }

    public static void setCenterLinesWidth(double value) {
        PREFERENCES.putDouble(CENTER_LINES_WIDTH, value);
    }

    public static double getCenterLinesWidth() {
        return PREFERENCES.getDouble(CENTER_LINES_WIDTH, DefaultValues.CENTER_LINES_WIDTH);
    }

    public static int getFrameLength() {
        return PREFERENCES.getInt(FRAME_LENGTH, DefaultValues.FRAME_LENGTH);
    }

    public static void setFrameLength(int value) {
        PREFERENCES.putInt(FRAME_LENGTH, value);
    }
}
