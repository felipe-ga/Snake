package com.snake.gui;

import com.snake.util.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeGame extends JFrame implements KeyListener {
    private static final Logger LOG = Logger.getLogger(SnakeGame.class.getName());
    private boolean close;
    public static int WIDTH_POINT = 10;
    private String title;
    private long speed;
    private Direction.directions directionCurrent;
    private HashMap<String,Integer> goals = new HashMap<>();
    private HashMap<String,Integer> speeds = new HashMap<>();
    private static SnakeGame INSTANCE;
    public static boolean GENERATE_FOOD = true;
    private HeadSnake snake;
    private List<Point> snakeBody;
    private Point food;
    private int width;
    private int height;
    private int score;
    private String level;
    private boolean gameOver;
    public SnakeGame(){
        goals.put("Easy",10);
        goals.put("Medium",20);
        goals.put("Hard",30);
        speeds.put("Easy",100);
        speeds.put("Medium",50);
        speeds.put("Hard",10);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event) {
                if (event.getSource() instanceof SnakeGame) {
                    ((SnakeGame) event.getSource()).exitFrame();
                }
            }
        });

        this.addKeyListener(this);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (dim.width / 2);
        this.height = (dim.height / 2);
        this.width -= (this.width % WIDTH_POINT);
        this.height -= (this.height % WIDTH_POINT);


        setResizable(false);
        setSize(new Dimension(width,height));
        setLocationRelativeTo(null);
    }

    public void startGame(){
        snakeBody = new ArrayList<>();
        this.snake = new HeadSnake(this.width / 2,this.height / 2);
        snakeBody.add(this.snake);
        directionCurrent = Direction.directions.RIGHT;
        setVisible(true);
    }

    public void generateFood() {
        Random rnd = new Random();
        int width = this.width / 2;
        int height = this.height / 2;
        int x = (int)(Math.random()*(this.width-WIDTH_POINT+1)+WIDTH_POINT);
        int y = (int)(Math.random()*(this.height-(WIDTH_POINT*3)+1)+(WIDTH_POINT*3));
        if((x % WIDTH_POINT) > 0) {
            x -= (x % WIDTH_POINT);
        }
        if(x < WIDTH_POINT) {
            x += WIDTH_POINT;
        }
        if(x > width) {
            x -= WIDTH_POINT;
        }
        if((y % WIDTH_POINT) > 0) {
            y = y - (y % WIDTH_POINT);
        }
        if(y > height) {
            y = y - WIDTH_POINT;
        }
        if(y < 0) {
            y = y + WIDTH_POINT;
        }
        food = new Point(x,y);
    }

    public static synchronized SnakeGame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SnakeGame();
        }
        return INSTANCE;
    }

    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        g.setColor(Color.blue);
        if(GENERATE_FOOD){
            generateFood();
            GENERATE_FOOD = false;
        }
        g.fillRect (this.food.x, this.food.y, WIDTH_POINT, WIDTH_POINT);
        g.setColor(Color.black);
        this.snakeBody
                .forEach(point -> g.fillRect(point.x, point.y, WIDTH_POINT, WIDTH_POINT));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT && this.directionCurrent != Direction.directions.RIGHT){
            this.directionCurrent = Direction.directions.LEFT;
        }else if(keyCode == KeyEvent.VK_RIGHT && this.directionCurrent != Direction.directions.LEFT){
            this.directionCurrent = Direction.directions.RIGHT;
        }else if(keyCode == KeyEvent.VK_UP && this.directionCurrent != Direction.directions.DOWN){
            this.directionCurrent = Direction.directions.UP;
        }else if(keyCode == KeyEvent.VK_DOWN && this.directionCurrent != Direction.directions.UP){
            this.directionCurrent = Direction.directions.DOWN;
        }else{
            LOG.log(Level.INFO,"Key not detected");
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public long getSpeed(){
        return this.speed;
    }
    public void setSpeed(String level){
        this.speed = speeds.get(level);
    }

    public Direction.directions getDirectionCurrent() {
        return directionCurrent;
    }

    public void setDirectionCurrent(Direction.directions directionCurrent) {
        this.directionCurrent = directionCurrent;
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }
    public void addPoint(Point p){
        if(this.snakeBody != null){
            this.snakeBody.add(p);
        }
    }
    public Point getPoint(int index){
        if(this.snakeBody != null){
            return this.snakeBody.get(index);
        }else{
            return null;
        }
    }
    public void setSnakeBody(List<Point> snakeBody) {
        this.snakeBody = snakeBody;
    }

    public Point getFood() {
        return food;
    }

    public void setFood(Point food) {
        this.food = food;
    }

    public HeadSnake getSnake() {
        return snake;
    }

    public void setSnake(HeadSnake snake) {
        this.snake = snake;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.setTitle(this.level);
    }

    public int getMaxPoint(){
        return this.goals.get(this.level);
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public void setTitle(String level){
        System.out.println(level);
        super.setTitle(String.format("[SNAKE GAME]   [LEVEL : %s]   [SCORE : %d]   [GOAL : %d]",level.toUpperCase(),this.getScore(),this.getMaxPoint()));
    }

    public void exitFrame(){
       this.close = true;
    }

    public boolean isClose() {
        return close;
    }
    public void setClose(boolean close) {
        this.close = close;
    }
}
