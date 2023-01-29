package dev.eureka.pathfinder.pathfinder;

import java.util.*;

/**
 * @author Eureka
 */

// I don't know if this algorithm already exist
public class PathFinder implements IPathFinder {
    private final List<Node> nodes;
    private List<Node> checked;
    private final List<Node> queue;

    private Node start;
    private Node goal;

    private int previousListSize;
    private boolean goalReached;

    private long ms;

    public PathFinder(List<Node> nodes) {
        this.nodes = nodes;
        this.checked = new ArrayList<>();
        this.queue = new ArrayList<>();

        this.previousListSize = 0;
        this.goalReached = false;
    }

    public PathFinder(List<Node> nodes, Node start, Node goal) {
        this(nodes);

        setStart(start);
        setGoal(goal);
    }

    public PathFinder visualize(long ms) {
        this.ms = ms;
        return this;
    }

    @Override
    public void search() {
        reset();

        Runnable code = () -> {
            while (!goalReached) {
                try {Thread.sleep(ms);} catch (InterruptedException e) {throw new RuntimeException(e);}

                previousListSize = checked.size();
                queue.clear();

                checked.forEach((node) -> {
                    if (node.getType().equals(PathType.CHECKED)) return;
                    node.setType(PathType.CHECKED); // Mark current as checked

                    List<Node> neighbours = getNeighbours(node);
                    neighbours.forEach((neighbour) -> neighbour.setParent(node));

                    // Check if someone in neighbours list equals goal node
                    if (neighbours.contains(goal)) {
                        goalReached = true;
                    }

                    queue.addAll(neighbours);
                });

                checked.addAll(queue);
                checked = new ArrayList<>(checked.stream().distinct().toList()); // Removing duplicates

                if (isPathBlocked()) throw new RuntimeException("Path is blocked.");
            }

            backTrack();
        };

        if (ms <= 0) {
            code.run();
        } else {
            new Thread(code).start();
        }
    }

    @Override
    public void reset() {
        nodes.forEach((node) -> {
            if (node.getType().equals(PathType.START) || node.getType().equals(PathType.GOAL) || node.getType().equals(PathType.WALL)) return;

            node.setType(PathType.NORMAL);
        });

        checked.clear();
        checked.add(start);

        goalReached = false;
    }

    @Override
    public List<Node> getNeighbours(Node cube) {
        return nodes.stream()
                .filter((node) -> Math.abs(cube.getX() - node.getX()) + Math.abs(cube.getY() - node.getY()) == 32) // 32 is a node size, that's why distance between neighbours is 32
                .filter((node) -> node.getType().equals(PathType.NORMAL) || node.getType().equals(PathType.GOAL)) // Ignores Goal because you need that in search() method to break while loop
                .toList();
    }

    @Override
    public boolean isPathBlocked() {
        return previousListSize == checked.size();
    }

    @Override
    public void backTrack() {
        Node current = goal;

        while (current != start) {
            try {Thread.sleep(ms);} catch (InterruptedException e) {throw new RuntimeException(e);}
            current = current.getParent();
            if (current == start) break;

            current.setType(PathType.PATH);
        }
    }

    public void setStart(Node start) {
        this.start = start.setType(PathType.START);
    }
    public void setGoal(Node goal) {
        this.goal = goal.setType(PathType.GOAL);
    }
}
