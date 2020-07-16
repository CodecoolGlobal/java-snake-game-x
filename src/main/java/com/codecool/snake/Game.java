package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.CircleEnemy;
import com.codecool.snake.entities.enemies.DownEnemy;
import com.codecool.snake.entities.enemies.UpEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Laser;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.util.List;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void restart() {
        snake.destroy();

        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (GameEntity item : gameObjs) {
            item.destroy();
        }
        init();
        start();

    }

    public void init() {
        spawnSnake();
        spawnEnemies(1);
        spawnPowerUps();

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();

    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }
    public int getSnakeLength(){
        return snake.bodyLength();
    }

    private void spawnSnake() {
        snake = new Snake(new Point2D(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; ++i) new CircleEnemy();
        for (int i = 0; i < numberOfEnemies; ++i) new DownEnemy(100, 100);
        for (int i = 0; i < numberOfEnemies; ++i) new UpEnemy(1450, 700);
    }

    private void spawnPowerUps() { new SimplePowerUp();}

    public void spawnLaser(){
        new Laser(snake);
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }


}
