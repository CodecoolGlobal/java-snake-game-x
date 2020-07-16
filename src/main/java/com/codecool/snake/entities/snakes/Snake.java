package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {
    private float speed = 2;
    private int health = 100;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Point2D position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public int bodyLength(){
        int length = 0;
        for(GameEntity part: body.getList()){
            length++;
        }
        return length;
    }

    public Point2D getSnakeHeadPosition(){
        return head.getHeadPosition();
    }

    public double getSnakeRotation(){
        return head.getRotate();
    }


    public void step() {
        SnakeControl turnDir = getUserInput();
        SnakeControl laser = getLaser();
        if(laser != SnakeControl.INVALID){
            if (bodyLength() > 6){
                Globals.getInstance().shootLaser();
            }
        }
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
        body.doPendingModifications();
        Globals.getInstance().extraSpawn();
        if(health < 100){
            Globals.getInstance().display.changeDisplayHealth(health);
        }
    }
    public void destroy(){
        this.head.destroy();
        for (GameEntity snakePart : this.body.getList() ) {
            snakePart.destroy();
        }
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    private SnakeControl getLaser(){
        SnakeControl laser = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.W)) laser = SnakeControl.LASER;
        InputHandler.getInstance().setKeyReleased(KeyCode.W);
        return laser;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) { health -= diff; }
    public void increaseHealth(int diff) { health += diff; }
    public void changeSpeed(float diff){ speed += diff; }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Globals.getInstance().showText(bodyLength());
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
