package com.codecool.snake;

import com.codecool.snake.entities.enemies.CircleEnemy;
import com.codecool.snake.entities.enemies.UpEnemy;
import com.codecool.snake.entities.powerups.PowerUpHealth;
import com.codecool.snake.entities.powerups.PowerUpSpeed;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.resources.Resources;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

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
        Text gameOver = new Text(530, 350, "GAME OVER!");
        gameOver.setFill(Color.WHITE);
        gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        display.removeScore();
        display.removeHealth();
        Text scoreText = new Text(710, 400, "SCORE: " + (length - 4));
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        display.add(gameOver);
        display.add(scoreText);
        Button restart = new Button("Restart");
        display.add(restart);
        restart.setStyle("-fx-font: 18 arial; -fx-base: #0e48ad;");
        restart.setLayoutX(725);
        restart.setLayoutY(450);
        restart.setOnAction(actionEvent -> {
            Globals.getInstance().stopGame();
            Globals.getInstance().restartGame();
        });
    }


    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpPizza", new Image("powerup_pizza.png"));
        resources.addImage("PowerUpSpeed", new Image("powerup_speed.png"));
        resources.addImage("PowerUpHealth", new Image("powerup_health.png"));
        resources.addImage("CircleEnemy", new Image("circle_enemy.png"));
        resources.addImage("UpEnemy", new Image("up_enemy.png"));
        resources.addImage("DownEnemy", new Image("down_enemy.png"));
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
            Globals.getInstance().display.removeAll();

        game.restart();
    }
    public void shootLaser(){
        game.spawnLaser();
    }

    int powerUp = 0;

    public void extraSpawn() {
        if (display.getPowerUpPizza() < 1){
            new SimplePowerUp();
            powerUp++;
        }
        switch (powerUp){
            case 2:
            case 4:
            case 8:
                if (display.getPowerUpSpeed() < 1){new PowerUpSpeed();}
                powerUp++;
                break;
            case 3:
            case 6:
            case 9:
                if (display.getPowerUpHealth() < 1){new PowerUpHealth();}
                powerUp++;
                break;
            default:
                break;
        }
        if(powerUp == 10){
            powerUp = 0;
        }
        if (display.getEnemyCount() < 1) {
            new CircleEnemy();
            new UpEnemy(100, 100);
            new UpEnemy(1450, 700);
        }
    }


    private Globals() {
        // singleton needs the class to have private constructor
    }
}
