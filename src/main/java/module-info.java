module com.mtanevski.art.durergrid {
    requires java.prefs;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.mtanevski.art.durergrid.components.preferences to javafx.fxml;
    exports com.mtanevski.art.durergrid;
}