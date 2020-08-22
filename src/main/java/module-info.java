module ExpCalc {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports pl.shymex.expcalc.Main to javafx.graphics;
    opens pl.shymex.expcalc.controller to javafx.fxml;
}