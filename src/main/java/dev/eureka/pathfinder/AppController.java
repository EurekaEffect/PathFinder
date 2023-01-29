package dev.eureka.pathfinder;

import dev.eureka.pathfinder.pathfinder.Node;
import dev.eureka.pathfinder.pathfinder.PathFinder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML private AnchorPane pane;

    private final List<Node> nodes = new ArrayList<>();
    private final PathFinder pathFinder = new PathFinder(nodes).visualize(20);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int x = 0; x < 15 * 32; x += 32) {
            for (int y = 0; y < 15 * 32; y += 32) {
                Node node = new Node(x, y);

                nodes.add(node);
                pane.getChildren().add(node.getRect());
            }
        }

        pathFinder.setStart(nodes.get(0));
        pathFinder.setGoal(nodes.get(nodes.size() - 1));
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }
}