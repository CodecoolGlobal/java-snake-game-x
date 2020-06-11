package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.CircleEnemy;
import com.codecool.snake.entities.enemies.LineEnemy;
import com.codecool.snake.entities.powerups.PowerUpHealth;
import com.codecool.snake.entities.powerups.PowerUpSpeed;
import com.codecool.snake.entities.powerups.SimplePowerUp;
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
        resources.addImage("Circle", new Image("clone.png"));
        resources.addImage("Line", new Image("droid.png"));
        resources.addImage("Laser", new Image("laser.png"));
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
        if (text.size() > 0) {
            Globals.getInstance().display.remove(text.get(0));
            Globals.getInstance().display.remove(text.get(1));
        }

        game.restart();
    }
    public void shootLaser(){
        game.spawnLaser();
    }

    int newSpawn = 10;

    public void extraSpawn() {

        List<GameEntity> gameObjs = display.getObjectList();
        if (gameObjs.size() <= newSpawn && gameObjs.size() != 0) {
            for (int i = 0; i < 2; ++i) new SimplePowerUp();
            for (int i = 0; i < 2; ++i) new PowerUpHealth();
            for (int i = 0; i < 2; ++i) new PowerUpSpeed();
            newSpawn+=3;
        }
        if (display.getEnemyCount() < 1) {
            new CircleEnemy();
            new LineEnemy(100, 100);
            new LineEnemy(1450, 700);
        }
    }

//    public int getSnakeHealth(){
//        return game.getSnakeHealth();
//    }


    private Globals() {
        // singleton needs the class to have private constructor
    }
}
