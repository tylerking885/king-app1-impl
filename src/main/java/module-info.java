module app {

    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    exports app;
    opens app to javafx.fxml;
}