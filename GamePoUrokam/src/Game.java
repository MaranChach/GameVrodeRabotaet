import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel implements ActionListener {

    private Timer timer;
    private Image Player;
    private int playerWidth = 40;
    private int playerHeight = 55;
    private Image Background;
    private Image Rock;
    private Image Money;
    private int rockWidth = 40;
    private int rockHeight = 40;
    private int moneyWidth = 40;
    private int moneyHeight = 40;


    private int rocksNum;
    private int playerX = 20;
    private int playerY = 20;
    private int[] rocksX;
    private int[] rocksY;

    private int[] moneyX;
    private int[] moneyY;

    private boolean inCollision = false;
    private int moneyNum;
    private int yourMoney;
    private Direction direction = Direction.None;

    public Game(){
        rocksNum = 5;
        moneyNum = 10;
        setBackground(Color.GREEN);
        setImages();
        initGame();
        addKeyListener(new KeyListener());
        setFocusable(true);
        System.out.println();
    }

    public void initGame(){
        rocksX = new int[rocksNum];
        rocksY = new int[rocksNum];

        moneyX = new int[moneyNum];
        moneyY = new int[moneyNum];

        timer = new Timer(50, this);
        timer.start();
        spawnRocks();
        spawnMoney();
    }

    public void spawnMoney(){
        for (int i = 0; i < moneyX.length; i++) {
            moneyX[i] = new Random().nextInt(350);
            moneyY[i] = new Random().nextInt(350);
        }
    }

    public void spawnRocks(){
        for (int i = 0; i < rocksX.length; i++) {
            rocksX[i] = new Random().nextInt(70,350);
            rocksY[i] = new Random().nextInt(350);
        }
    }

    public void checkCollisions(){
        for (int i = 0; i < rocksX.length; i++) {
            if ((rocksX[i] < (playerX+playerWidth) && (rocksX[i]+rockWidth) > playerX) && (rocksY[i] < (playerY+playerHeight) && (rocksY[i]+rockWidth) > playerY)){
                switch (direction) {
                    case Up : playerY+=5; break;
                    case Down : playerY-=5; break;
                    case Right : playerX-=5; break;
                    case Left : playerX+=5; break;
                }
            }
        }

        for (int i = 0; i < moneyX.length; i++) {
            if ((moneyX[i] < (playerX+playerWidth) && (moneyX[i]+moneyWidth) > playerX) && (moneyY[i] < (playerY+playerHeight) && (moneyY[i]+moneyWidth) > playerY)){
                moneyX[i] = 1000;
                yourMoney += 1;
            }
        }
    }

    public void setImages(){
        ImageIcon plIcon = new ImageIcon("GamePoUrokam/images/4head.png");
        Player = plIcon.getImage();
        ImageIcon backIcon = new ImageIcon("GamePoUrokam/images/grass.png");
        Background = backIcon.getImage();
        ImageIcon rockIcon = new ImageIcon("GamePoUrokam/images/stone.png");
        Rock = rockIcon.getImage();
        ImageIcon moneyIcon = new ImageIcon("GamePoUrokam/images/money.png");
        Money = moneyIcon.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        checkCollisions();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Player, playerX, playerY,40, 55, this);
        g.drawString("Your money = " + yourMoney, 0, 350);
        for (int i = 0; i < rocksX.length; i++) {
            g.drawImage(Rock, rocksX[i], rocksY[i] , 40, 40, this);
        }
        for (int i = 0; i < moneyX.length; i++) {
            g.drawImage(Money, moneyX[i], moneyY[i] , 40, 40, this);
        }
    }

    public void move(){
        switch (direction){
            case Up:
                playerY -= 5;
                break;
            case Down:
                playerY += 5;
                break;
            case Right:
                playerX += 5;
                break;
            case Left:
                playerX -= 5;
                break;
            case None:
                break;
        }
    }

    public class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_RIGHT : direction = Direction.Right; break;
                case KeyEvent.VK_LEFT : direction = Direction.Left; break;
                case KeyEvent.VK_UP : direction = Direction.Up; break;
                case KeyEvent.VK_DOWN : direction = Direction.Down; break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_RIGHT : direction = Direction.None; break;
                case KeyEvent.VK_LEFT : direction = Direction.None; break;
                case KeyEvent.VK_UP : direction = Direction.None; break;
                case KeyEvent.VK_DOWN : direction = Direction.None; break;
            }
        }
    }
}