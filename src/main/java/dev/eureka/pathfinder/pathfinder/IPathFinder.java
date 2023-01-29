package dev.eureka.pathfinder.pathfinder;

import java.util.List;

public interface IPathFinder {
    void search();

    void reset();

    List<Node> getNeighbours(Node node);

    boolean isPathBlocked();

    void backTrack();
}
