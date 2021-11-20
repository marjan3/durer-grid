module com.mtanevski.art.durer.grid {
    requires java.prefs;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.mtanevski.art.durer.grid to javafx.fxml;
    exports com.mtanevski.art.durer.grid;
}