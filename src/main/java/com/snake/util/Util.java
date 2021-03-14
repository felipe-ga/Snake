package com.snake.util;

public class Util {
    public static String convertLevelToString(int level){
        switch (level){
            case 1:
                return "Easy";
            case 2:
                return "Medium";
            default:
                return "Hard";
        }
    }
}
