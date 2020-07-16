package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.PowerUpHealth;
import com.codecool.snake.entities.powerups.PowerUpSpeed;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import javafx.geometry.Point2D;

import java.util.Timer;
import java.util.TimerTask;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Point2D position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    public Point2D getHeadPosition() {
        return this.getPosition();
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            System.out.println(getMessage());
            snake.changeHealth(25);
        }
        if (entity instanceof SimplePowerUp) {
            System.out.println(getMessage());
            Globals.getInstance().display.changeScore();
            snake.addPart(1);
        }
        if (entity instanceof PowerUpSpeed) {
            snake.changeSpeed(2f);
            Timer myTimer = new Timer();
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Globals.getInstance().display.displayTimer(String.valueOf(5));
                }
            }, 1000);
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Globals.getInstance().display.displayTimer(String.valueOf(4));
                }
            }, 2000);
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Globals.getInstance().display.displayTimer(String.valueOf(3));
                }
            }, 3000);
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Globals.getInstance().display.displayTimer(String.valueOf(2));
                }
            }, 4000);
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Globals.getInstance().display.displayTimer(String.valueOf(1));
                }
            }, 5000);
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Globals.getInstance().display.displayTimer(" ");
                    snake.changeSpeed(-2f);
                }
            }, 6000);
            Globals.getInstance().display.changeScore();
            System.out.println("Snake speed is " + snake.getSpeed());
        }
        if (entity instanceof PowerUpHealth) {
            snake.increaseHealth(10);
            Globals.getInstance().display.changeScore();
            System.out.println("Snake health is " + snake.getHealth());
        }

    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }

}
