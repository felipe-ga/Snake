package com.snake.gui;

import com.snake.util.StatesGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Thread{
    private static final Logger LOG = Logger.getLogger(Menu.class.getName());
    private StatesGame.State state;
    private SnakeGame snakeFrame;
    private String level;
    private boolean close;
    public void startGame(String level){
        snakeFrame = SnakeGame.getInstance();
        snakeFrame.setLevel(level);
        snakeFrame.setSpeed(level);
        snakeFrame.setScore(this.snakeFrame.getScore());
        state = StatesGame.State.NONE;
        snakeFrame.startGame();
        snakeFrame.setClose(false);
        snakeFrame.GENERATE_FOOD = true;
        start();
    }
    public void run() {
        while(state == StatesGame.State.NONE){
            try {
                sleep(snakeFrame.getSpeed());
                this.snakeFrame.getSnake().calculateXAndYByDirection(snakeFrame.getDirectionCurrent());
                if(snakeFrame.getSnake().isSnakeCrashed(snakeFrame.getWidth(), snakeFrame.getHeight())){
                    state = StatesGame.State.LOST;
                    break;
                }
                this.snakeFrame.getSnakeBody().add(0,new Point(this.snakeFrame.getSnake().x,this.snakeFrame.getSnake().y));
                this.snakeFrame.getSnakeBody().remove(this.snakeFrame.getSnakeBody().size() - 1);

                if(this.snakeFrame.getSnake().isSnakeCrashedMyself(this.snakeFrame.getSnakeBody())){
                    state = StatesGame.State.LOST;
                }

                if(this.snakeFrame.getSnake().snakeToEatFood(this.snakeFrame.getFood())){
                    this.snakeFrame.getSnakeBody().add(0,new Point(this.snakeFrame.getSnake().x,this.snakeFrame.getSnake().y));
                    this.snakeFrame.setScore(this.snakeFrame.getScore()+1);
                    this.snakeFrame.GENERATE_FOOD = true;
                }

                if(this.snakeFrame.getScore() == this.snakeFrame.getMaxPoint()){
                    state = StatesGame.State.WIN;

                }
                snakeFrame.repaint();
            } catch (InterruptedException e) {
                LOG.log(Level.INFO,e.getMessage());
            }
        }
        if(!this.snakeFrame.isClose()){
            if(this.state == StatesGame.State.WIN){
                JOptionPane.showMessageDialog(null,"You win");
            }else if(this.state == StatesGame.State.LOST){
                JOptionPane.showMessageDialog(null,"Game Over");
            }
            this.snakeFrame.dispose();
        }
    }
}
