package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import java.awt.*;
import java.sql.BatchUpdateException;
import java.util.List;
import java.util.logging.Handler;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;



public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();
    Button restart = new Button("Restart");

    public Display(Pane pane) {
        displayPane = pane;
        displayPane.getChildren().add(restart);
        restart.setOnAction(actionEvent -> {
            Globals.getInstance().stopGame();
            Globals.getInstance().restartGame();
        });
    }

    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
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

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
}
