package net.ackx.pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ball extends GameObject {

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public enum Angle {
        NORMAL_UP, NORMAL_DOWN
    }

    public enum PointScored {
        LEFT_PLAYER, RIGHT_PLAYER, NONE
    }

    private static final double SIZE = 25.0;
    private static final double MOVEMENT_INCREMENT = 4.0;

    private Direction direction;
    private Angle angle;

    Ball() {
        super(new Rectangle(SIZE, SIZE, Color.WHITE));

        if (GameState.DEBUG) {
            // Easier to track the ball
            direction = Direction.LEFT_TO_RIGHT;
            angle = Angle.NORMAL_DOWN;
        } else {
            Random random = new Random();
            direction = random.nextBoolean() ? Direction.LEFT_TO_RIGHT : Direction.RIGHT_TO_LEFT;
            angle = random.nextBoolean() ? Angle.NORMAL_UP : Angle.NORMAL_DOWN;
        }
    }

    public PointScored move() {
        PointScored scored = PointScored.NONE;

        // Detect touch top or bottom edge of screen and bounce
        if (node.getTranslateY() + SIZE >= getPanelHeight()) {
            angle = Angle.NORMAL_UP;
        } else if (node.getTranslateY() <= 0.0) {
            angle = Angle.NORMAL_DOWN;
        }

        // Detect touch left or right edge of screen and wrap
        if (node.getTranslateX() > getPanelWidth() + SIZE) {
            node.setTranslateX(-SIZE);
            scored = PointScored.LEFT_PLAYER;
        } else if (node.getTranslateX() < -SIZE) {
            node.setTranslateX(getPanelWidth());
            scored = PointScored.RIGHT_PLAYER;
        }

        // Compute next position based on direction and angle
        double dx = direction == Direction.LEFT_TO_RIGHT ? MOVEMENT_INCREMENT : -MOVEMENT_INCREMENT;
        node.setTranslateX(node.getTranslateX() + dx);
        double dy = angle == Angle.NORMAL_UP ? -MOVEMENT_INCREMENT : MOVEMENT_INCREMENT;
        node.setTranslateY(node.getTranslateY() + dy);

        return scored;
    }

    public Direction getDirection() {
        return direction;
    }

    public void switchDirection() {
        direction = direction == Direction.LEFT_TO_RIGHT ? Direction.RIGHT_TO_LEFT : Direction.LEFT_TO_RIGHT;
    }
}
