package com.snake.gui;

import com.snake.util.Direction;

import java.awt.*;

public class BodySnake extends Point {
    private Direction.directions direction;

    public BodySnake(int x, int y, Direction.directions direction){
        super(x,y);
        this.direction = direction;
    }
    public Direction.directions getDirection() {
        return direction;
    }

    public void setDirection(Direction.directions direction) {
        this.direction = direction;
    }
}
