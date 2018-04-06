package net.ackx.pong;

/**
 * Player is a convenient holder for a pad and a score.
 *
 * It does not participate in the game UI.
 */
public class Player {
    private Pad pad;
    private Score score;

    Player(Pad pad, Score score) {
        this.pad = pad;
        this.score = score;
    }

    public Pad getPad() {
        return pad;
    }

    public Score getScore() {
        return score;
    }
}
