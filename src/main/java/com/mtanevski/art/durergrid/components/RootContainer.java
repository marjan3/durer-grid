package com.mtanevski.art.durergrid.components;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class RootContainer extends GridPane {

    private final RowConstraints sideRowTop;
    private final ColumnConstraints sideColumnLeft;
    private final RowConstraints sideRowBottom;
    private final ColumnConstraints sideColumnRight;


    public RootContainer() {
        super();
        sideColumnLeft = new ColumnConstraints();
        sideColumnLeft.setHalignment(HPos.CENTER);
        sideColumnLeft.setHgrow(Priority.ALWAYS);
        sideColumnRight = new ColumnConstraints();
        sideColumnRight.setHalignment(HPos.CENTER);
        sideColumnRight.setHgrow(Priority.ALWAYS);
        ColumnConstraints centerColumn = new ColumnConstraints();
        centerColumn.setHalignment(HPos.CENTER);
        centerColumn.setHgrow(Priority.NEVER);
        this.getColumnConstraints().addAll(sideColumnLeft, centerColumn, sideColumnRight);
        sideRowTop = new RowConstraints();
        sideRowTop.setValignment(VPos.CENTER);
        sideRowTop.setVgrow(Priority.ALWAYS);
        sideRowBottom = new RowConstraints();
        sideRowBottom.setValignment(VPos.CENTER);
        sideRowBottom.setVgrow(Priority.ALWAYS);
        final RowConstraints centerRow = new RowConstraints();
        centerRow.setValignment(VPos.CENTER);
        centerRow.setVgrow(Priority.NEVER);
        this.getRowConstraints().addAll(sideRowTop, centerRow, sideRowBottom);
    }

    public double getFrameLength() {
        return Math.max(sideColumnLeft.getMinWidth(),
                Math.max(sideColumnRight.getMinWidth(),
                        Math.max(sideRowTop.getMinHeight(), sideRowBottom.getMinHeight())));
    }

    public void setFrameLength(double value) {
        sideColumnLeft.setMinWidth(value);
        sideColumnRight.setMinWidth(value);
        sideRowTop.setMinHeight(value);
        sideRowBottom.setMinHeight(value);
    }
}
