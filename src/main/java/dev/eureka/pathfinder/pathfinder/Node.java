package dev.eureka.pathfinder.pathfinder;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Node {
    private Node parent;
    private Rectangle rect;

    private int x;
    private int y;

    private PathType type;

    public Node(int x, int y) {
        rect = new Rectangle();

        rect.setTranslateX(this.x = x);
        rect.setTranslateY(this.y = y);
        rect.setWidth(32);
        rect.setHeight(32);

        rect.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            switch (event.getButton()) {
                case PRIMARY -> setType(PathType.WALL);
                case SECONDARY -> setType(PathType.NORMAL);
            }
        });

        setType(PathType.NORMAL);
    }

    public Node setType(PathType type) {
        if (this.type != null) {
            if (this.type.equals(PathType.START) || this.type.equals(PathType.GOAL)) return this;
        }

        switch (this.type = type) {
            case START -> rect.setFill(Paint.valueOf("red"));
            case GOAL -> rect.setFill(Paint.valueOf("blue"));
            case NORMAL -> rect.setFill(Paint.valueOf("#c6c6c6"));
            case CHECKED -> rect.setFill(Paint.valueOf("yellow"));
            case PATH -> rect.setFill(Paint.valueOf("green"));
            case WALL -> rect.setFill(Paint.valueOf("#000000"));
        }

        return this;
    }

    public Node getParent() {
        return parent;
    }
    public Rectangle getRect() {
        return rect;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public PathType getType() {
        return type;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
