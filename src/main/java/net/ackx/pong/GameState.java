package net.ackx.pong;

public class GameState {
    public static final double WIDTH = 1_200.0;
    public static final double HEIGHT = 800.0;
    public static final boolean DEBUG = false;

    private Player leftPlayer;
    private Player rightPlayer;
    private Ball ball;

    GameState(Player leftPlayer, Player rightPlayer, Ball ball) {
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.ball = ball;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public Ball getBall() {
        return ball;
    }
}
