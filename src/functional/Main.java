package functional;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Main extends JPanel {

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    final int QUADRANT_SIZE = 64;

    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;

    final int TOP_Y = BF_HEIGHT - QUADRANT_SIZE;
    final int TOP_X = BF_WIDTH - QUADRANT_SIZE;

    String[][] objects = {
            {"B", "B", "B", "G", "G", "W", "G", "W", "B"},
            {"G", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "B", "B", "G", "G", "G", "G", "G", "B"},
            {"B", "B", "B", "G", "G", "G", "G", "B", "B"},
            {"B", "B", "B", "G", "G", "G", "G", "B", "B"},
            {"G", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "G", "G", "G", "G", "W", "G", "W", "B"},
            {"G", "B", "B", "G", "G", "W", "G", "W", "B"},
            {"B", "B", "B", "G", "G", "W", "G", "W", "B"}};

    //1 - UP, 2 - Down, 3 - Left, 4 - Right
    int direction = 3;

    int bulletX = -100;
    int bulletY = -100;

    int tankX = 256;
    int tankY = 256;

    public static void main(String[] args) throws InterruptedException {
        Main game = new Main();
        //game.runTheGame();
    }

    Main() throws InterruptedException {
        JFrame frame = new JFrame("Dandy Tanks");
        frame.setMinimumSize(new Dimension(BF_WIDTH, BF_HEIGHT + 44));
        frame.getContentPane().add(this);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) {
                    try {
                        move(UP);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else if (key == KeyEvent.VK_S) {
                    try {
                        move(DOWN);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else if (key == KeyEvent.VK_A) {
                    try {
                        move(LEFT);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else if (key == KeyEvent.VK_D) {
                    try {
                        move(RIGHT);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
//        frame.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                try {
//                    fire();
//                } catch (
//                        InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        for (int y = 0; y < objects.length; y++) {
            for (int x = 0; x < objects[y].length; x++) {
                switch (objects[y][x]) {
                    case "B":
                        g.setColor(new Color(155, 38, 38, 255));
                        break;
                    case "W":
                        g.setColor(new Color(62, 62, 252));
                        break;
                    case "G":
                        g.setColor(new Color(238, 218, 201));
                        break;
                }
                g.fillRect(x * QUADRANT_SIZE, y * QUADRANT_SIZE, QUADRANT_SIZE, QUADRANT_SIZE);
            }
        }

        //draw tank
        g.setColor(Color.RED);
        g.fillRect(tankX, tankY, QUADRANT_SIZE, QUADRANT_SIZE);

        //draw gun
        g.setColor(Color.GREEN);
        if (direction == 1) {
            g.fillRect(tankX + 20, tankY, 24, 34);
        } else if (direction == 2) {
            g.fillRect(tankX + 20, tankY + 30, 24, 34);
        } else if (direction == 3) {
            g.fillRect(tankX, tankY + 20, 34, 24);
        } else if (direction == 4) {
            g.fillRect(tankX + 30, tankY + 20, 34, 24);
        }

        //draw bullet
        g.setColor(Color.YELLOW);
        g.fillRect(bulletX, bulletY, 14, 14);
    }

    void runTheGame() throws InterruptedException {
        while (true) {
            fire();
            //moveRandom();
            //moveToQuadrant( 0, 0);
        }
    }

    void move(int direction) throws InterruptedException {
        this.direction = direction;

        if (dontCanMove()) {
            System.out.println("Can`t move!!!");
            fire();
            return;
        }

        for (int i = 0; i < QUADRANT_SIZE; i++) {

            if (direction == 1) {
                tankY--;
            } else if (direction == 2) {
                tankY++;
            } else if (direction == 3) {
                tankX--;
            } else if (direction == 4) {
                tankX++;
            }
            repaint();
        }

        Thread.sleep(33);
    }

    void moveToQuadrant(int y, int x) throws InterruptedException {
        if(y > tankY){
            while (y != tankY)
            move(DOWN);
        }else if(y < tankY){
            while (y != tankY)
                move(UP);
        }else if(x > tankX){
            while (x != tankX)
                move(RIGHT);
        }else if(x < tankX){
            while (x != tankX)
                move(LEFT);
        }
    }

    void moveRandom() throws InterruptedException {
        Random random = new Random();
        int direction = random.nextInt(4) + 1;
        move(direction);
    }

    boolean dontCanMove() {
        return (direction == UP && tankY == 0) || (direction == DOWN && tankY == TOP_Y)
                || (direction == LEFT && tankX == 0) || (direction == RIGHT && tankX == TOP_X)
                || (nextObject(direction).equals("B"));
    }

    String nextObject(int direction) {
        int y = tankY;
        int x = tankX;

        switch (direction) {
            case UP:
                y -= 64;
                break;
            case DOWN:
                y += 64;
                break;
            case LEFT:
                x -= 64;
                break;
            case RIGHT:
                x += 64;
                break;
        }
        return objects[y / QUADRANT_SIZE][x / QUADRANT_SIZE];
    }

    boolean processInterception() {
        int y = bulletY / 64;
        int x = bulletX / 64;

        if (y <= 8 && x <= 8 && objects[y][x].equals("B")) {
            objects[y][x] = "G";
            return true;
        }

        return false;
    }

    void fire() throws InterruptedException {
        bulletX = tankX + 25;
        bulletY = tankY + 25;

        while (bulletX > 0 && bulletX < BF_WIDTH && bulletY > 0 && bulletY < BF_HEIGHT) {
            switch (direction) {
                case 1:
                    bulletY--;
                    break;
                case 2:
                    bulletY++;
                    break;
                case 3:
                    bulletX--;
                    break;
                case 4:
                    bulletX++;
                    break;
            }

            if (processInterception()) {
                destroyBullet();
            }

            Thread.sleep(10);
            repaint();
        }
        destroyBullet();
    }

    void destroyBullet() {
        bulletX = -100;
        bulletY = -100;
        repaint();
    }

}
