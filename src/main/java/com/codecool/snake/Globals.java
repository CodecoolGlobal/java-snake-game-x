package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.resources.Resources;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.List;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1500;
    public static final double WINDOW_HEIGHT = 800;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if (instance == null) instance = new Globals();
        return instance;
    }


    public void showText(int length) {
        Text gameOver = new Text(500, 300, "GAME OVER!");
        Text score = new Text(500, 350, "Your snake's length is " + length);
        display.add(gameOver);
        display.add(score);
    }


    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("PowerUpSpeed", new Image("powerup_speed.png"));
        resources.addImage("PowerUpHealth", new Image("powerup_health.png"));
    }

    public Image getImage(String name) {
        return resources.getImage(name);
    }

    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }

    public void restartGame() {
        List<Node> text = Globals.getInstance().display.getScreenText();
        if(text.size() > 0){
            Globals.getInstance().display.remove(text.get(0));
            Globals.getInstance().display.remove(text.get(1));
        }

        game.restart();
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
