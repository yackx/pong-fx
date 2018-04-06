package net.ackx.pong;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends GameObject {
    private int score = 0;

    private static Text buildText() {
        Text t = new Text("0");
        t.setFont(new Font("Consolas", 80));
        t.setFill(Color.WHITE);
        return t;
    }

    Score() {
        super(buildText());
    }

    public void increase() {
        score++;
        if (score == 100) {
            score = 0;
        }
        getText().setText(Integer.toString(score));
    }

    private Text getText() {
        return (Text) node;
    }
}
