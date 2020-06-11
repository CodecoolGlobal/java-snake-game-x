package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

import javafx.geometry.Point2D;


public class CircleEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    double enemyRotation = getRotate();
    double turnRate = 0.7;
    int speed = 3;

    public CircleEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        setX(900);
        setY(400);

        setRotate(5);

        int speed = 1;
        heading = Utils.directionToVector(enemyRotation, speed);
    }


    @Override
    public void step() {


        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        enemyRotation -= turnRate;
        heading = Utils.directionToVector(enemyRotation, speed);
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
        if(entity instanceof Laser){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
