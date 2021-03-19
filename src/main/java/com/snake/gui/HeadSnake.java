package com.snake.gui;

import com.snake.util.Direction;
import com.snake.util.StatesGame;

import java.awt.*;
import java.util.List;

public class HeadSnake extends Point {
    public HeadSnake(int x, int y){
        super(x,y);
    }
    public void calculateXAndYByDirection(Direction.directions direction){
        if(direction == Direction.directions.RIGHT){
            this.x += SnakeGame.WIDTH_POINT;
        } else if(direction == Direction.directions.LEFT){
            this.x -= SnakeGame.WIDTH_POINT;
        } else if(direction == Direction.directions.UP){
            this.y -= SnakeGame.WIDTH_POINT;
        } else if(direction == Direction.directions.DOWN){
            this.y += SnakeGame.WIDTH_POINT;
        }
    }
    public boolean isSnakeCrashed(int width, int height){
        if(this.x <= (width % SnakeGame.WIDTH_POINT)
                || this.x >= (width - SnakeGame.WIDTH_POINT) - (width % SnakeGame.WIDTH_POINT)
                || this.y <= (SnakeGame.WIDTH_POINT * 2) - (height % SnakeGame.WIDTH_POINT)
                || this.y >= (height - SnakeGame.WIDTH_POINT) - (height % SnakeGame.WIDTH_POINT)){

            return true;
        }else{
            return false;
        }
    }

    public boolean isSnakeCrashedMyself(List<Point> snakeBody){
        int i = 0;
        for(Point point : snakeBody){
            if(i > 0){
                if(this.x == point.x && this.y  == point.y) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public boolean snakeToEatFood(Point f){
        if(this.x >= f.x
                && this.x <= f.x + SnakeGame.WIDTH_POINT
                && this.y >= f.y
                && this.y <= f.y + SnakeGame.WIDTH_POINT){
            return true;
        }else{
            return false;
        }
    }

}
