module dev.eureka.pathfinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.eureka.pathfinder to javafx.fxml;
    exports dev.eureka.pathfinder;
    exports dev.eureka.pathfinder.pathfinder;
    opens dev.eureka.pathfinder.pathfinder to javafx.fxml;
}