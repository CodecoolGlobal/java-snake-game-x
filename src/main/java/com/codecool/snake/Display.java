package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import java.awt.*;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


import com.codecool.snake.entities.enemies.CircleEnemy;
import com.codecool.snake.entities.enemies.LineEnemy;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;



public class Display {
    private Text score;
    private Text healthText;
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();
    private List<Node> screenText = new ArrayList<>();
    Button restart = new Button("Restart");
    int enemyCount = 0;
    int width = 100;
    Rectangle healtbarSnake= new Rectangle(width, 25.0, Color.RED);
    Rectangle healthBackground = new Rectangle(120, 35, Color.BLACK);



    public Display(Pane pane) {
        displayPane = pane;
        displayPane.getChildren().add(restart);
        displayPane.getChildren().add(healthBackground);
        healthBackground.setX(1290);
        healthBackground.setY(5);
        displayPane.getChildren().add(healtbarSnake);
        healtbarSnake.setX(1300);
        healtbarSnake.setY(10);
        Text health = new Text(1315, 25, "HEALTH");
        displayPane.getChildren().add(health);
        health.setFill(Color.WHITE);


        restart.setOnAction(actionEvent -> {
            Globals.getInstance().stopGame();
            Globals.getInstance().restartGame();
        });
    }


    public void changeDisplayHealth(int newHealth){
        healtbarSnake.setWidth(newHealth);
    }



    public void add(GameEntity entity) {
        if(entity instanceof CircleEnemy || entity instanceof LineEnemy){
            enemyCount++;
        }
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }
    public void add(Node node){
        displayPane.getChildren().add(node);
        screenText.add(node);
    }

    public void remove(GameEntity entity) {
        if(entity instanceof CircleEnemy || entity instanceof LineEnemy){
            enemyCount--;
        }
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public void remove(Node node){
        displayPane.getChildren().remove(node);
    }

    public void removeAllText(){
        screenText.clear();
    }

    public List<Node> getScreenText(){
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

    public int getEnemyCount(){
        return enemyCount;
    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
}
