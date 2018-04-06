package net.ackx.pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pad extends GameObject {
    public enum Direction {
        UP, DOWN, IDLE
    }

    private static final double MOVEMENT_INCREMENT = 6.0;

    private Direction direction = Direction.IDLE;

    Pad() {
        super(new Rectangle(25.0, 100.0, Color.WHITE));
    }

    public void move() {
        switch (direction) {
            case UP: moveUp(); break;
            case DOWN: moveDown(); break;
        }
    }

    private void moveUp() {
        double y = node.getTranslateY();
        if (y > 0) {
            node.setTranslateY(y - MOVEMENT_INCREMENT);
        }
    }

    private void moveDown() {
        double y = node.getTranslateY();
        if (y < 1_200 - node.getLayoutY()) {
            node.setTranslateY(y + MOVEMENT_INCREMENT);
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
