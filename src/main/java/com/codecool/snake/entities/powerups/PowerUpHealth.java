package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

public class PowerUpHealth extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public PowerUpHealth() {
        setImage(Globals.getInstance().getImage("PowerUpHealth"));

        setX((rnd.nextDouble() * (Globals.WINDOW_WIDTH - 250)) + 150);
        setY((rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 250)) + 150);
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up health :)";
    }
}
