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



public class LineEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    double direction;
    int speed;
    boolean goingLeft = false;
    boolean goingRight = true;

    public LineEnemy(double X, double Y) {
        super(10);

        setImage(Globals.getInstance().getImage("Line"));
        setX(X);
        setY(Y);

        double direction = 90;
        setRotate(direction);

        int speed = 3;
        heading = Utils.directionToVector(direction, speed);
    }


    @Override
    public void step() {
        if (goingLeft) {
            setX((getX() + heading.getX()) - 15);
        }
        else if(goingRight){
            setX((getX() + heading.getX()) + 15);
        }
        if(getX() >= 1450){
            goingRight = false;
            goingLeft = true;
        }
        if(getX() <= 50){
            goingRight = true;
            goingLeft = false;
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
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

