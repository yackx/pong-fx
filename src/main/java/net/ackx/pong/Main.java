package net.ackx.pong;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private Pane root;
    private GameState gameState;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(GameState.WIDTH, GameState.HEIGHT);

        Pad leftPad = new Pad();
        leftPad.getNode().setTranslateX(60.0);
        leftPad.getNode().setTranslateY(100.0);
        addGameObject(leftPad);

        Score leftScore = new Score();
        leftScore.getNode().setTranslateX(400.0);
        leftScore.getNode().setTranslateY(100.0);
        addGameObject(leftScore);

        Player leftPlayer = new Player(leftPad, leftScore);

        Pad rightPad = new Pad();
        rightPad.getNode().setTranslateX(GameState.WIDTH - 60.0 - 25.0);
        rightPad.getNode().setTranslateY(200.0);
        addGameObject(rightPad);

        Score rightScore = new Score();
        rightScore.getNode().setTranslateX(GameState.WIDTH - 400.0);
        rightScore.getNode().setTranslateY(100.0);
        addGameObject(rightScore);

        Player rightPlayer = new Player(rightPad, rightScore);

        Ball ball = new Ball();
        ball.getNode().setTranslateY(Math.round(GameState.HEIGHT / 3));
        ball.getNode().setTranslateX(Math.round(GameState.WIDTH / 2));
        addGameObject(ball);

        gameState = new GameState(leftPlayer, rightPlayer, ball);

        // Game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        }.start();

        return root;
    }

    private void onUpdate() {
        Player leftPlayer = gameState.getLeftPlayer();
        Player rightPlayer = gameState.getRightPlayer();
        Ball ball = gameState.getBall();

        leftPlayer.getPad().move();
        rightPlayer.getPad().move();

        Ball.PointScored scored = ball.move();
        switch (scored) {
            case LEFT_PLAYER: leftPlayer.getScore().increase(); break;
            case RIGHT_PLAYER: rightPlayer.getScore().increase(); break;
        }

        if (ball.getDirection() == Ball.Direction.LEFT_TO_RIGHT) {
            if (ball.isCollidingWith(rightPlayer.getPad())) {
                ball.switchDirection();
            }
        } else {
            if (ball.isCollidingWith(leftPlayer.getPad())) {
                ball.switchDirection();
            }
        }
    }

    private void addGameObject(GameObject gameObject) {
        root.getChildren().add(gameObject.getNode());
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent(), Color.BLACK);
        Pad leftPad = gameState.getLeftPlayer().getPad();
        Pad rightPad = gameState.getRightPlayer().getPad();

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: rightPad.setDirection(Pad.Direction.UP); break;
                case DOWN: rightPad.setDirection(Pad.Direction.DOWN); break;
                case W: leftPad.setDirection(Pad.Direction.UP); break;
                case S: leftPad.setDirection(Pad.Direction.DOWN); break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP: case DOWN:
                    rightPad.setDirection(Pad.Direction.IDLE);
                    break;
                case W: case S:
                    leftPad.setDirection(Pad.Direction.IDLE);
                    break;
            }
        });

        // Cursor will disappear after a short while and reappear on mouse move.
        // Drawback: the cursor may be missing shortly even the mouse moves outside our app.
        PauseTransition idle = new PauseTransition(Duration.seconds(3));
        idle.setOnFinished(e -> scene.setCursor(Cursor.NONE));
        idle.play();
        scene.setOnMouseMoved(e -> {
            scene.setCursor(Cursor.DEFAULT);
            idle.playFromStart();
        });

        primaryStage.setTitle("Pong FX");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
