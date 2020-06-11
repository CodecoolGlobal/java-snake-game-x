package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import javafx.geometry.Point2D;

public class Laser extends GameEntity implements Animatable, Interactable {
    Point2D laserDirection;


    public Laser(Snake snake) {
        setImage(Globals.getInstance().getImage("Laser"));
        Point2D coord = snake.getSnakeHeadPosition();
        setX(coord.getX());
        setY(coord.getY());
        double direction = snake.getSnakeRotation();
        setRotate(direction);

        int speed = 7;
        laserDirection = Utils.directionToVector( direction, speed);
    }

    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setY(getY() + laserDirection.getY());
        setX(getX() + laserDirection.getX());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            destroy();
        }
        if(isOutOfBounds()){
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return ("Ouchhh!");
    }
}

