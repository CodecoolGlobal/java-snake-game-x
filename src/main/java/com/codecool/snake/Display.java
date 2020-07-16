package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.CircleEnemy;
import com.codecool.snake.entities.enemies.UpEnemy;
import com.codecool.snake.entities.powerups.PowerUpHealth;
import com.codecool.snake.entities.powerups.PowerUpSpeed;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Display {
    private Text healthText;
    private int timer = 5;
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();
    private List<Node> screenText = new ArrayList<>();
    int enemyCount = 0;
    int powerUpPizza = 0;
    int powerUpSpeed = 0;
    int powerUpHealth = 0;
    int width = 100;
    Rectangle healthBarSnake = new Rectangle(width, 25.0, Color.RED);
    Rectangle healthBackground = new Rectangle(120, 35, Color.WHITE);
    Rectangle header = new Rectangle(1500, 50, Color.BLACK);
    Text score = new Text(50, 35, "SCORE: 0");
    Text timerText = new Text(715, 30, " ");


    public Display(Pane pane) {
        displayPane = pane;
        displayPane.getChildren().add(header);
        displayPane.getChildren().add(healthBackground);
        healthBackground.setX(1290);
        healthBackground.setY(5);
        displayPane.getChildren().add(healthBarSnake);
        healthBarSnake.setX(1300);
        healthBarSnake.setY(10);
        header.setFill(Color.BLACK);
        Text health = new Text(1315, 25, "HEALTH");
        displayPane.getChildren().add(health);
        health.setFill(Color.WHITE);
        displayPane.getChildren().add(score);
        score.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        timerText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score.setFill(Color.WHITE);
        displayPane.getChildren().add(timerText);
        timerText.setFill(Color.WHITE);

    }


    public void changeDisplayHealth(int newHealth) {
        healthBarSnake.setWidth(newHealth);
    }

    public int getTimer() {
        return timer;
    }

    public void displayTimer(String timer) {
        timerText.setText(timer);
    }

    public void changeScore() {
        score.setText("SCORE: " + (Globals.getInstance().game.getSnakeLength() - 3));
    }
    public void removeScore() {displayPane.getChildren().remove(score);}
    public void resetScore() { score.setText("SCORE: 0"); }

    public Text getScore() {
        return score;
    }

    public void add(GameEntity entity) {
        if (entity instanceof CircleEnemy || entity instanceof UpEnemy) {
            enemyCount++;
        }
        if (entity instanceof PowerUpHealth) {
            powerUpHealth++;
        }
        if (entity instanceof PowerUpSpeed) {
            powerUpSpeed++;
        }
        if (entity instanceof SimplePowerUp) {
            powerUpPizza++;
        }
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void add(Node node) {
        displayPane.getChildren().add(node);
        screenText.add(node);
    }

    public void remove(GameEntity entity) {
        if (entity instanceof CircleEnemy || entity instanceof UpEnemy) {
            enemyCount--;
        }
        if (entity instanceof PowerUpHealth) {
            powerUpHealth--;
        }
        if (entity instanceof PowerUpSpeed) {
            powerUpSpeed--;
        }
        if (entity instanceof SimplePowerUp) {
            powerUpPizza--;
        }
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public void remove(Node node) {
        displayPane.getChildren().remove(node);
    }

    public void removeAll() {
        displayPane.getChildren().clear();
        resetScore();
        changeDisplayHealth(100);
        displayPane.getChildren().add(header);
        displayPane.getChildren().add(score);
        displayPane.getChildren().add(healthBackground);
        displayPane.getChildren().add(healthBarSnake);
        displayPane.getChildren().add(timerText);
    }

    public List<Node> getScreenText() {
        return screenText;
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);

    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public int getPowerUpPizza() {
        return powerUpPizza;
    }

    public int getPowerUpSpeed() {
        return powerUpSpeed;
    }

    public int getPowerUpHealth() {
        return powerUpHealth;
    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
}
