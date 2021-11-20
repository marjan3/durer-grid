package com.mtanevski.art.durergrid;

import javafx.scene.paint.Color;

public class DefaultValues {

    // Preferences
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color GRID_LINES_COLOR = Color.BLACK;
    public static final Color CENTER_LINES_COLOR = Color.valueOf("#b31a1a");
    public static final double GRID_LINES_WIDTH = 1.0;
    public static final double CENTER_LINES_WIDTH = 2.0;
    public static final int FRAME_LENGTH = 100;

    // Other
    public static final Color WINDOW_COLOR = Color.TRANSPARENT;
    public static final int INCREMENT = 50;
    public static final int MIN_INCREMENT = 5;
    public static final double BUTTONS_SPACING = 7.0;
    public static final int MIN_WIDTH = 300;
    public static final int MAX_WIDTH = 3840;
    public static final int MIN_HEIGHT = 100;
    public static final int MAX_HEIGHT = 2160;
    public static final double MIN_LINES_WIDTH = 0.1;
    public static final double MAX_LINES_WIDTH = 20.0;
    public static final int MIN_FRAME_LENGTH = 70;
    public static final int MAX_FRAME_LENGTH = MIN_WIDTH - MIN_FRAME_LENGTH;
    public static final int RESIZE_BORDER = 5;
}
